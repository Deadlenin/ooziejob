package ru.ooziejobstatus.models;

import ru.ooziejobstatus.entities.JobOozie;

import java.util.List;
import java.util.Objects;

public class ReportFrontApi {
    private Long id;
    private String ReportName;
    private List<JobApi> Jobs;

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

    public List<JobApi> getJobs() {
        return Jobs;
    }

    public void setJobs(List<JobApi> jobs) {
        Jobs = jobs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportFrontApi that = (ReportFrontApi) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(ReportName, that.ReportName) &&
                Objects.equals(Jobs, that.Jobs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ReportName, Jobs);
    }
}
