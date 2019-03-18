package ru.ooziejobstatus.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ooziejobstatus.entities.JobOozie;
import ru.ooziejobstatus.entities.Report;
import ru.ooziejobstatus.exception.NotFoundException;
import ru.ooziejobstatus.models.ReportResponse;
import ru.ooziejobstatus.models.ReportWithJobsResponse;
import ru.ooziejobstatus.models.Response;
import ru.ooziejobstatus.repos.ReportRepository;

import java.util.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class MappingController {
    @Autowired
    private ReportRepository reportRepository;

    @GetMapping("/")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<ReportResponse>> list() {
        List<Report> reports = reportRepository.findAll();
        List<ReportResponse> response = new ArrayList<>();
        for (int i = 0; i <reports.size() ; i++) {
            ReportResponse repItem = new ReportResponse();
            repItem.setId(reports.get(i).getId());
            repItem.setReportName(reports.get(i).getReportPath());
            response.add(repItem);
        }
        return new ResponseEntity<>(response, OK);
    }
    @GetMapping(value = "/get")
    @CrossOrigin(origins = "*")
    public ResponseEntity<ReportWithJobsResponse> get(@RequestParam("id") Long id) {
        Optional<Report> one = reportRepository.findById(id);
                if (!one.isPresent())
            throw new NotFoundException("Report with id " + id + " does not exists");
        ReportWithJobsResponse response = new ReportWithJobsResponse();
        Report rep = one.get();
        response.setId(rep.getId());
        response.setReportName(rep.getReportPath().split("home/reports")[1].substring(1));
        List<JobOozie> jl = rep.getJobNamesList();
        response.setJobs( rep.getJobNamesList());
        return new ResponseEntity<>(response, OK);
    }

}
