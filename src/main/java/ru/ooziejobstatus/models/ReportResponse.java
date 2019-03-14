package ru.ooziejobstatus.models;

import java.util.Objects;

public class ReportResponse {
    private Long id;

    public String getReportName() {
        return ReportName;
    }

    public void setReportName(String reportName) {
        ReportName = reportName;
    }

    private String ReportName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportResponse that = (ReportResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(ReportName, that.ReportName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ReportName);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
