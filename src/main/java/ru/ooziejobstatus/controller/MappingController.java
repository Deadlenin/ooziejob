package ru.ooziejobstatus.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ooziejobstatus.entities.JobOozie;
import ru.ooziejobstatus.entities.Report;
import ru.ooziejobstatus.exception.NotFoundException;
import ru.ooziejobstatus.models.ReportFrontApi;
import ru.ooziejobstatus.models.ReportResponse;
import ru.ooziejobstatus.models.ReportApi;
import ru.ooziejobstatus.repos.ReportRepository;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        for (int i = 0; i < reports.size(); i++) {
            ReportResponse repItem = new ReportResponse();
            repItem.setId(reports.get(i).getId());
            repItem.setReportName(reports.get(i).getReportPath());
            response.add(repItem);
        }
        return new ResponseEntity<>(response, OK);
    }

    @GetMapping(value = "/get")
    @CrossOrigin(origins = "*")
    public ResponseEntity<ReportApi> get(@RequestParam("id") Long id) {
        Optional<Report> one = reportRepository.findById(id);
        if (!one.isPresent())
            throw new NotFoundException("Report with id " + id + " does not exists");
        ReportApi response = new ReportApi();
        Report rep = one.get();
        response.setId(rep.getId());
        response.setReportName(rep.getReportPath().split("home/reports")[1].substring(1));
        List<JobOozie> jl = rep.getJobNamesList();
        response.setJobs(rep.getJobNamesList());
        return new ResponseEntity<>(response, OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @CrossOrigin(origins = "*")
    public void delete(@RequestParam("id") @NotNull Long id) {
        Optional<Report> one = reportRepository.findById(id);
        if (!one.isPresent())
            throw new NotFoundException("Report with id " + id + " does not exists");
        Report rep = one.get();
        reportRepository.delete(rep);

    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Report save(@RequestPart("reportApi") ReportFrontApi report) {
        Long id = report.getId();
        String rNmae = report.getReportName();
        Optional<Report> one = reportRepository.findById(id);
        if (!one.isPresent())
            throw new NotFoundException("Report with id " + id + " does not exists");
        Report rep = one.get();
        rep.setReportPath("/home/reports/".concat(rNmae));
        reportRepository.save(rep);
        return rep;
    }

}
