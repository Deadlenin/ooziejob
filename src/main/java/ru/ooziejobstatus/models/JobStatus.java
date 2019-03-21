package ru.ooziejobstatus.models;

import java.util.Objects;

public class JobStatus {

    private String reportPath;
    private String status;
    private String appName;
    private String createdTime;
    private String startTime;
    private String lastModTime;
    private String endTime;
    private Integer jobType;
    private String consoleUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobStatus jobStatus = (JobStatus)o;
        return Objects.equals(reportPath, jobStatus.reportPath) &&
                Objects.equals(status, jobStatus.status) &&
                Objects.equals(appName, jobStatus.appName) &&
                Objects.equals(createdTime, jobStatus.createdTime) &&
                Objects.equals(startTime, jobStatus.startTime) &&
                Objects.equals(lastModTime, jobStatus.lastModTime) &&
                Objects.equals(endTime, jobStatus.endTime) &&
                Objects.equals(consoleUrl, jobStatus.consoleUrl) &&
                Objects.equals(jobType, jobStatus.jobType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(reportPath, status, appName, createdTime, startTime, lastModTime, endTime, consoleUrl, jobType);
    }



    public JobStatus() {
    }

    public Integer getJobType() {
        return jobType;
    }

    public void setJobType(Integer jobType) {
        this.jobType = jobType;
    }

    public String getConsoleUrl() {
        return consoleUrl;
    }

    public void setConsoleUrl(String consoleUrl) {
        this.consoleUrl = consoleUrl;
    }

    public String getReportPath() {
        return reportPath;
    }

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getLastModTime() {
        return lastModTime;
    }

    public void setLastModTime(String lastModTime) {
        this.lastModTime = lastModTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

}