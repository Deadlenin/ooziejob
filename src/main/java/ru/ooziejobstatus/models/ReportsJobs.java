package ru.ooziejobstatus.models;

import java.util.List;
import java.util.Objects;

public class ReportsJobs {
    private String reportPath;
    private List<String> jobList;

    public String getReportPath() {
        return reportPath;
    }

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }

    public List<String> getJobList() {
        return jobList;
    }

    public void setJobList(List<String> jobList) {
        this.jobList = jobList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportsJobs that = (ReportsJobs)o;
        return Objects.equals(reportPath, that.reportPath) &&
                Objects.equals(jobList, that.jobList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reportPath, jobList);
    }
}
