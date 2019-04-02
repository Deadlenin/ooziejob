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
    private String reportPath;

    @OneToMany(mappedBy = "report", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobOozie> jobList;


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
        return reportPath;
    }

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath.trim();
    }

    public List<JobOozie> getJobList() {
        if (jobList == null)
            jobList = new ArrayList<>();
        return jobList;
    }

    public Report addJobOozie(JobOozie jobOozie){
        jobOozie.setReport(this);
        getJobList().add(jobOozie);
        return this;
    }

    public void setJobList(List<JobOozie> JobList) {
        this.jobList = JobList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report that = (Report)o;
        return Objects.equals(id, that.id) &&
                Objects.equals(reportPath, that.reportPath) &&
                Objects.equals(jobList, that.jobList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reportPath);
    }
}
