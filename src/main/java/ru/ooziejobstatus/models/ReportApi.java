package ru.ooziejobstatus.models;

import ru.ooziejobstatus.entities.JobOozie;

import java.util.List;
import java.util.Objects;

public class ReportApi {

    private Long id;
    private String ReportName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReportName() {
        return ReportName;
    }

    public void setReportName(String reportName) {
        ReportName = reportName;
    }



//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getReportName() {
//        return ReportName;
//    }
//
//    public void setReportName(String reportName) {
//        ReportName = reportName;
//    }
//
//    public List<JobOozie> getJobs() {
//        return Jobs;
//    }
//
//    public void setJobs(List<JobOozie> jobs) {
//        Jobs = jobs;
//    }
}
