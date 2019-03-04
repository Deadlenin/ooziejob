package ru.ooziejobstatus.models;

import java.util.List;
import java.util.Objects;

public class JobsForReport {
    private String ReportPath;
    private List<String> JobNames;

    public String getReportPath() {
        return this.ReportPath;
    }

    public void setReportPath(String reportPath) {
        this.ReportPath = reportPath;
    }

    public List<String> getJobNames() {
        return this.JobNames;
    }

    public void setJobNames(List<String> jobNames) {
        this.JobNames = jobNames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobsForReport that = (JobsForReport)o;
        return Objects.equals(ReportPath, that.ReportPath) &&
                Objects.equals(JobNames, that.JobNames);
    }

    @Override
    public int hashCode() {

        return Objects.hash(ReportPath, JobNames);
    }
}
