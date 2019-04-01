package ru.ooziejobstatus.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import ru.ooziejobstatus.entities.JobOozie;
import ru.ooziejobstatus.entities.Report;
import ru.ooziejobstatus.models.JobStatus;
import ru.ooziejobstatus.models.Response;
import ru.ooziejobstatus.repos.ReportRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@RestController
public class StatusControllerCors {
    @Autowired
    private ReportRepository reportRepository;
    @CrossOrigin(origins = "*")
    @PostMapping("/oozie")
    public ResponseEntity<List<Response>> list(@RequestPart("json") List<String> json) {
        List<Response> responses = new ArrayList<>(); // инициализация списка результатов которые вернутся в ответ на запрос от pentaho
        try {
            List<Report> reports = reportRepository.findAll(); // получение списка репортов со списком зависимых джобов
            for (int i = 0; i < reports.size(); i++) {
                Response respItem = new Response();
                Report currentReport = reports.get(i);
                respItem.setReportPath(currentReport.getReportPath());
                List<JobOozie> jobs = (List<JobOozie>)currentReport.getJobList();// массив job'ов из бд
                List<JobStatus> jobList = new ArrayList<>(); // инициализация списка статусов
                for (int j = 0; j < jobs.size(); j++) {
                    JobOozie job = jobs.get(j);
                    JobStatus jobStatus = new JobStatus();
                    if (!job.getJobName().trim().equals("-")) {
                        URL oozieUrl = new URL("http://d01dc-ctrl303.main.russianpost.ru:11000/oozie/v1/jobs?filter=user=hdfs;name="
                                .concat(job.getJobName().trim()).concat("&offset=1&len=1"));
                        HttpURLConnection conn = (HttpURLConnection)oozieUrl.openConnection();
                        conn.setRequestMethod("GET");
                        conn.setRequestProperty("Accept", "application/json");
                        if (conn.getResponseCode() != 200) {
                            throw new RuntimeException("Failed : HTTP error code : "
                                    + conn.getResponseCode());
                        }
                        BufferedReader br = new BufferedReader(new InputStreamReader(
                                (conn.getInputStream())));
                        StringBuilder sb = new StringBuilder();
                        for (String line; (line = br.readLine()) != null; sb.append(line)) ;
                        String oozieResponse = sb.toString();
                        try {
                            JSONObject jsonObj = new JSONObject(oozieResponse);
                            Iterator<String> keyItr = jsonObj.keys();
                            while (keyItr.hasNext()) {
                                String key = keyItr.next();
                                jsonObj.getString(key);
                                if (key.equals("workflows")) {
                                    if (jsonObj.getString(key).contains("status")) {
                                        try {
                                            JSONObject workflow = new JSONObject(jsonObj.getString(key).substring(1, jsonObj.getString(key).length() - 1));
                                            Iterator<String> workfowItr = workflow.keys();

                                            while (workfowItr.hasNext()) {
                                                String flowkey = workfowItr.next();
                                                switch (flowkey) {
                                                    case "status":
                                                        jobStatus.setStatus(workflow.getString(flowkey));
                                                        break;
                                                    case "appName":
                                                        jobStatus.setAppName(workflow.getString(flowkey));
                                                        break;
                                                    case "createdTime":
                                                        jobStatus.setCreatedTime(workflow.getString(flowkey));
                                                        break;
                                                    case "startTime":
                                                        jobStatus.setStartTime(workflow.getString(flowkey));
                                                        break;
                                                    case "lastModTime":
                                                        jobStatus.setLastModTime(workflow.getString(flowkey));
                                                        break;
                                                    case "endTime":
                                                        jobStatus.setEndTime(workflow.getString(flowkey));
                                                        break;
                                                    case "consoleUrl":
                                                        jobStatus.setConsoleUrl(workflow.getString(flowkey));
                                                        break;
                                                }
                                            }
                                            conn.disconnect();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            conn.disconnect();
                                        }
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            conn.disconnect();
                            e.printStackTrace();
                            return new ResponseEntity<>(responses, HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                        conn.disconnect();
                    } else {
                        jobStatus.setStatus("streaming job");
                        jobList.add(jobStatus);
                        continue;
                    }
                    if (jobStatus.getStatus() != null ) {
                        if(job.getIdJobType() == 2) {
                            jobStatus.setJobType(2);
                            jobList.add(jobStatus);
                        }
                        else if(job.getIdJobType() == 1){
                            jobStatus.setJobType(1);
                            jobList.add(jobStatus);
                        }
                    }
                }// end of jobs
                respItem.setJobList(jobList);
                responses.add(respItem);
            }// end of reports
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(responses, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity<>(responses, HttpStatus.OK);
        }
    }
}