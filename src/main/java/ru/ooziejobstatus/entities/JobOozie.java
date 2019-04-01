package ru.ooziejobstatus.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "job_oozie")
@Cacheable(false)
public class JobOozie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

    @Basic
    @Column(name = "job_name")
    private String JobName;

    public void setIdJobType(Integer idJobType) {
        IdJobType = idJobType;
    }

    @Basic
    @Column(name = "id_job_type")
    private Integer IdJobType;

    public JobOozie() {
    }

    public JobOozie(String jobName) {
        this.JobName = jobName;
    }

    public JobOozie(String jobName, Integer idJobType) {
        this.JobName = jobName;
        this.IdJobType = idJobType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobName() {
        return JobName;
    }

    public void setJobName(String jobName) {
        this.JobName = jobName;
    }

    public Integer getIdJobType() {
        return IdJobType;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report reportByIdReport) {
        this.report = reportByIdReport;
    }

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_report_pentaho", referencedColumnName = "id")
    private Report report;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobOozie jobOozie = (JobOozie)o;
        return Objects.equals(id, jobOozie.id) &&
                Objects.equals(JobName, jobOozie.JobName) &&
                Objects.equals(IdJobType, jobOozie.IdJobType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, JobName, IdJobType);
    }

}
