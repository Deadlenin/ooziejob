package ru.ooziejobstatus.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "report_pentaho")
@Cacheable(false)
public class Report implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Integer id;

    @Basic
    @Column(name = "report_path")
    private String ReportPath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReportPath() {
        return ReportPath;
    }

    public void setReportPath(String reportPath) {
        ReportPath = reportPath.trim();
    }

    @OneToMany(mappedBy = "report",
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<JobOozie> JobNamesList;

    public Collection<JobOozie> getJobNamesList() {
        if (JobNamesList == null)
            JobNamesList = new ArrayList<>();
        return JobNamesList;
    }

    public void setJobNamesList(Collection<JobOozie> JobNamesList) {
        this.JobNamesList = JobNamesList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report that = (Report)o;
        return Objects.equals(id, that.id) &&
                Objects.equals(ReportPath, that.ReportPath) &&
                Objects.equals(JobNamesList, that.JobNamesList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ReportPath);
    }
}
