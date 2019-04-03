package ru.ooziejobstatus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ooziejobstatus.entities.Report;
import ru.ooziejobstatus.models.ReportResponse;
import ru.ooziejobstatus.repos.ReportRepository;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/dashboards/")
@CrossOrigin(origins = "*")

public class DashboardController {
//    @Autowired
//    private ReportRepository reportRepository;
//
//    @GetMapping("/")
//    public ResponseEntity<List<ReportResponse>> list() {
//        List<Report> reports = reportRepository.findAll();
//        List<ReportResponse> response = new ArrayList<>();
////        for (int i = 0; i <reports.size() ; i++) {
////            ReportResponse repItem = new ReportResponse();
////            repItem.setId(reports.get(i).getId());
////            repItem.setReportName(reports.get(i).getReportPath());
////            response.add(repItem);
////        }
//        return new ResponseEntity<>(response, OK);
//    }
}
