package ru.ooziejobstatus.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ooziejobstatus.entities.JobOozie;
import ru.ooziejobstatus.entities.Report;
import ru.ooziejobstatus.exception.NotFoundException;
import ru.ooziejobstatus.models.ReportResponse;
import ru.ooziejobstatus.models.ReportWithJobsResponse;
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

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @CrossOrigin(origins = "*")
    public void delete(@RequestParam("id") @NotNull Long id) {
        Optional<Report> one = reportRepository.findById(id);
        Report rep = reportRepository.getOne(id);
        Report r2 = (Report)one.get();
        if(rep != null) {
            reportRepository.delete(rep);
        }

    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Report save() {
        ReportWithJobsResponse r = new ReportWithJobsResponse();
        Report report = new Report();
        Long id= 50l;
        report.setId(id);
        report.setReportPath("/home/reports/tttttt/KPI   ttttttt.wcdf");
        JobOozie job = new JobOozie();
        job.setJobName("new job");
        job.setJobName("555");
        job.setJobType(1);
        job.setReport(report);
        List<JobOozie> jobs = new ArrayList<>();
        jobs.add(job);
        report.setJobNamesList(jobs);

        reportRepository.save(report);
        return report;
        //return _criteriaService.save(criterionApi);
    }

}
