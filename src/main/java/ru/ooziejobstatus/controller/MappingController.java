package ru.ooziejobstatus.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ooziejobstatus.entities.JobOozie;
import ru.ooziejobstatus.entities.Report;
import ru.ooziejobstatus.exception.NotFoundException;
import ru.ooziejobstatus.models.JobApi;
import ru.ooziejobstatus.models.ReportFrontApi;
import ru.ooziejobstatus.models.ReportResponse;
import ru.ooziejobstatus.models.ReportApi;
import ru.ooziejobstatus.repos.ReportRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;


@RestController
public class MappingController {
    private class NameTypeColletion {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            NameTypeColletion that = (NameTypeColletion) o;
            return Objects.equals(jName, that.jName) &&
                    Objects.equals(jType, that.jType);
        }

        @Override
        public int hashCode() {
            return Objects.hash(jName, jType);
        }

        private String jName;
        private Integer jType;

        public String getjName() {
            return jName;
        }

        public void setjName(String jName) {
            this.jName = jName;
        }

        public Integer getjType() {
            return jType;
        }

        public void setjType(Integer jType) {
            this.jType = jType;
        }

        public NameTypeColletion(String jName, Integer jType) {
            this.jName = jName;
            this.jType = jType;
        }
    }

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
        response.sort(Comparator.comparing(ReportResponse::getId));
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
        List<JobOozie> jl = rep.getJobList();

        response.setJobs(rep.getJobList());
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
        String rNmae = report.getReportName();
        List<JobApi> frontJobs = report.getJobs();
        Report reportToSave = new Report();
        reportToSave.setReportPath("/home/reports/".concat(rNmae));
        frontJobs.forEach(job-> reportToSave.addJobOozie(new JobOozie(job.getJobName(), job.getJobType())));
        Report respReport = reportRepository.saveAndFlush(reportToSave);
        return respReport;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Report edit(@RequestPart("reportApi") ReportFrontApi report) {
        Long id = report.getId();
        String rNmae = report.getReportName();
        Optional<Report> one = reportRepository.findById(id);
        if (!one.isPresent())
            throw new NotFoundException("Report with id " + id + " does not exists");
        Report reportServer = one.get();
        reportServer.setReportPath("/home/reports/".concat(rNmae));

        List<JobApi> frontJobs = report.getJobs();

        List<String> jobNames = new ArrayList<>();
        frontJobs.forEach(el -> jobNames.add(el.getJobName()));

        List<Integer> frontJobTypes = new ArrayList<>();
        frontJobs.forEach(el -> frontJobTypes.add(el.getJobType()));

        List<NameTypeColletion> frontJobCollection = new ArrayList<>();
        frontJobs.forEach(el -> frontJobCollection.add(new NameTypeColletion(el.getJobName(), el.getJobType())));


        List<JobOozie> deleteJobs = reportServer.getJobList().stream()
                .filter(x -> !jobNames.contains(x.getJobName()) || !frontJobTypes.contains(x.getJobType()))
                .collect(Collectors.toList());

        reportServer.getJobList().removeAll(deleteJobs);
        deleteJobs.forEach(job-> job.setReport(null));

        List<JobOozie> dbJobs = reportServer.getJobList();
        List<NameTypeColletion> tcl = new ArrayList<>();
        dbJobs.forEach(el -> tcl.add(new NameTypeColletion(el.getJobName(), el.getJobType())));

        List<NameTypeColletion> addJob = frontJobCollection.stream()
                .filter(x -> !tcl.contains(x))
                .collect(Collectors.toList());

        addJob.forEach(x-> reportServer.addJobOozie(new JobOozie(x.getjName(), x.getjType())));
        Report rep = reportRepository.saveAndFlush(reportServer);
        return rep;
    }

}
