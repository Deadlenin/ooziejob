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
import ru.ooziejobstatus.repos.JobRepositary;
import ru.ooziejobstatus.repos.ReportRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@RestController
public class StatusControllerCors {
    @Autowired
    private ReportRepository reportRepository;

    @CrossOrigin(origins = "*")
    @PostMapping("/oozie")
    public ResponseEntity<List<JobStatus>> list(@RequestPart("json") List<String> json) {
        List<JobStatus> result = new ArrayList<>();
        try {
            List<Report> reports = reportRepository.findAll();
            for (int i = 0; i < reports.size(); i++) {
                Report currentReport = reports.get(i);
                List<JobOozie> jobs = (List<JobOozie>)currentReport.getJobNamesList();
                for (int j = 0; j <jobs.size() ; j++) {
                    JobOozie job = jobs.get(j);
                    if(!job.getJobName().trim().equals("-")) {
                        URL oozieUrl = new URL("http://d01dc-ctrl303.main.russianpost.ru:11000/oozie/v1/jobs?filter=user=hdfs;name="
                                .concat(job.getJobName().trim()).concat("&offset=1&len=1"));
                    }
                    else{
                        break;
                    }
                }
            }
            List<URL> urls = new ArrayList<>();
            try {
                for (int i = 0; i < urls.size(); i++) {
                    HttpURLConnection conn = (HttpURLConnection)urls.get(i).openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Accept", "application/json");
                    if (conn.getResponseCode() != 200) {
                        throw new RuntimeException("Failed : HTTP error code : "
                                + conn.getResponseCode());
                    }
                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            (conn.getInputStream())));
                    StringBuilder sb = new StringBuilder();
                    for (String line; (line = br.readLine()) != null; sb.append(line));
                    String response = sb.toString();
                    try {
                        JSONObject jsonObj = new JSONObject(response);
                        Iterator<String> keyItr = jsonObj.keys();
                        while (keyItr.hasNext()) {
                            String key = keyItr.next();
                            jsonObj.getString(key);
                            if (key.equals("workflows")) {
                                if(jsonObj.getString(key).contains("status")) {
                                    try {
                                        JSONObject workflow = new JSONObject(jsonObj.getString(key).substring(1, jsonObj.getString(key).length() - 1));
                                        Iterator<String> workfowItr = workflow.keys();
                                        JobStatus resp = new JobStatus();
                                        resp.setReportPath(urls.get(i).toString());
                                        while (workfowItr.hasNext()) {
                                            String flowkey = workfowItr.next();
                                            switch (flowkey) {
                                                case "status":
                                                    resp.setStatus(workflow.getString(flowkey));
                                                    break;
                                                case "appName":
                                                    resp.setAppName(workflow.getString(flowkey));
                                                    break;
                                                case "createdTime":
                                                    resp.setCreatedTime(workflow.getString(flowkey));
                                                    break;
                                                case "startTime":
                                                    resp.setStartTime(workflow.getString(flowkey));
                                                    break;
                                                case "lastModTime":
                                                    resp.setLastModTime(workflow.getString(flowkey));
                                                    break;
                                                case "endTime":
                                                    resp.setEndTime(workflow.getString(flowkey));
                                                    break;
                                                case "consoleUrl":
                                                    resp.setConsoleUrl(workflow.getString(flowkey));
                                                    break;
                                            }
                                        }
                                        result.add(resp);
                                        conn.disconnect();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        conn.disconnect();
                                    }
                                }
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                    conn.disconnect();
                }
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

