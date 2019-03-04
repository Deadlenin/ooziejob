package ru.ooziejobstatus.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import ru.ooziejobstatus.models.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@RestController
public class StatusControllerCors {
    @CrossOrigin(origins = "*")
    @PostMapping("/oozie")
    public ResponseEntity<List<Response>> list(@RequestPart("json") List<String> json) {
        List<Response> result = new ArrayList<>();
        try {

            for (int i = 0; i <json.size() ; i++) {
                String reportPath = json.get(i);
            }
            URL url = new URL("http://d01dc-ctrl303.main.russianpost.ru:11000/oozie/v1/jobs?filter=user=hdfs;name=NDU-CVPP&offset=1&len=1");
            URL url2 = new URL("http://d01dc-ctrl303.main.russianpost.ru:11000/oozie/v1/jobs?filter=user=hdfs;name=NDU-CVPP&offset=1&len=1");
            List<URL> urls = new ArrayList<>();
            urls.add(url);
            urls.add(url2);
            HttpURLConnection conn = null;
            try {
                for (int i = 0; i < urls.size(); i++) {
                    conn = (HttpURLConnection)urls.get(i).openConnection();
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
                                try {
                                    JSONObject workflow = new JSONObject(jsonObj.getString(key).substring(1, jsonObj.getString(key).length() -1));
                                    Iterator<String> workfowItr = workflow.keys();
                                    Response resp = new Response();
                                    resp.setReportPath(urls.get(i).toString());
                                    while (workfowItr.hasNext()) {
                                        String flowkey = workfowItr.next();
                                        switch (flowkey){
                                            case "status":
                                                resp.setStatus( workflow.getString(flowkey));
                                                break;
                                            case "appName":
                                                resp.setAppName( workflow.getString(flowkey));
                                                break;
                                            case "createdTime":
                                                resp.setCreatedTime( workflow.getString(flowkey));
                                                break;
                                            case "startTime":
                                                resp.setStartTime( workflow.getString(flowkey));
                                                break;
                                            case "lastModTime":
                                                resp.setLastModTime( workflow.getString(flowkey));
                                                break;
                                            case "endTime":
                                                resp.setEndTime( workflow.getString(flowkey));
                                                break;
                                            case "consoleUrl":
                                                resp.setConsoleUrl( workflow.getString(flowkey));
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
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                    conn.disconnect();
                }
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            } finally {
                conn.disconnect();
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }
}

