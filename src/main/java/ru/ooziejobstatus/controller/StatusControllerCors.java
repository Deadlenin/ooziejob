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
//
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}