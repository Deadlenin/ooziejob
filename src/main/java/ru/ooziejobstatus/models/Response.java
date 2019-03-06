package ru.ooziejobstatus.models;

import java.util.List;
import java.util.Objects;

public class Response {
    private String reportPath;
    private List<JobStatus> jobList;

    public Response() {
    }

    public String getReportPath() {
        return reportPath;
    }

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }

    public List<JobStatus> getJobList() {
        return jobList;
    }

    public void addJob(JobStatus jobStatus) {
        this.getJobList().add(jobStatus);
    }

    public void setJobList(List<JobStatus> jobList) {
        this.jobList = jobList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response that = (Response)o;
        return Objects.equals(reportPath, that.reportPath) &&
                Objects.equals(jobList, that.jobList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reportPath, jobList);
    }
}
