package ru.ooziejobstatus.models;

import java.util.Objects;

public class Response {

    private String reportPath;
    private String status;
    private String appName;
    private String createdTime;
    private String startTime;
    private String lastModTime;
    private String endTime;
    private String consoleUrl;

    public Response() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response)o;
        return Objects.equals(reportPath, response.reportPath) &&
                Objects.equals(status, response.status) &&
                Objects.equals(appName, response.appName) &&
                Objects.equals(createdTime, response.createdTime) &&
                Objects.equals(startTime, response.startTime) &&
                Objects.equals(lastModTime, response.lastModTime) &&
                Objects.equals(endTime, response.endTime) &&
                Objects.equals(consoleUrl, response.consoleUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reportPath, status, appName, createdTime, startTime, lastModTime, endTime);
    }

}