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
