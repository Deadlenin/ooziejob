package ru.ooziejobstatus.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "report_pentaho")
@Cacheable(false)
public class Report implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

    @Basic
    @Column(name = "report_path")
    private String ReportPath;

    @OneToMany(mappedBy = "report", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobOozie> JobList;
    private Report report;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setReport(Report reportByIdReport) {
        this.report = reportByIdReport;
    }

    public Report getReport() {
        return report;
    }


    public String getReportPath() {
        return ReportPath;
    }

    public void setReportPath(String reportPath) {
        ReportPath = reportPath.trim();
    }

    public List<JobOozie> getJobList() {
        if (JobList == null)
            JobList = new ArrayList<>();
        return JobList;
    }

    public Report addJobOozie(JobOozie jobOozie){
        jobOozie.setReport(this);
        getJobList().add(jobOozie);
        return this;
    }

    public void setJobList(List<JobOozie> JobList) {
        this.JobList = JobList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report that = (Report)o;
        return Objects.equals(id, that.id) &&
                Objects.equals(ReportPath, that.ReportPath) &&
                Objects.equals(JobList, that.JobList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ReportPath);
    }
}
