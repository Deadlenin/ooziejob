package ru.ooziejobstatus.models;

import java.util.List;
import java.util.Objects;

public class ReportFrontApi {
    private Long id;
    private String reportName;
    private List<JobApi> jobs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public List<JobApi> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobApi> jobs) {
        this.jobs = jobs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportFrontApi that = (ReportFrontApi) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(reportName, that.reportName) &&
                Objects.equals(jobs, that.jobs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reportName, jobs);
    }
}
