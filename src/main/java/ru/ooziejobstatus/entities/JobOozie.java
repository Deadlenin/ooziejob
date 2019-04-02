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
    private String jobName;

    public void setIdJobType(Integer idJobType) {
        this.idJobType = idJobType;
    }

    @Basic
    @Column(name = "id_job_type")
    private Integer idJobType;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_job_type", referencedColumnName = "id", updatable = false, insertable = false)
    private JobType jobType;


    public JobOozie() {
    }

    public JobOozie(String jobName) {
        this.jobName = jobName;
    }

    public JobOozie(String jobName, Integer idJobType) {
        this.jobName = jobName;
        this.idJobType = idJobType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Integer getIdJobType() {
        return idJobType;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report reportByIdReport) {
        this.report = reportByIdReport;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_report_pentaho")
    private Report report;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobOozie jobOozie = (JobOozie) o;
        return Objects.equals(id, jobOozie.id) &&
                Objects.equals(jobName, jobOozie.jobName) &&
                Objects.equals(idJobType, jobOozie.idJobType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, jobName, idJobType);
    }

}
