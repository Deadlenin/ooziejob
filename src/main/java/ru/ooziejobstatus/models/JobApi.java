package ru.ooziejobstatus.models;

import java.util.Objects;

public class JobApi {
    private Long id;
    private String jobName;
    private Integer idJobType;
    private String typeName;

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
    public void setIdJobType(Integer idJobType) {
        this.idJobType = idJobType;
    }
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobApi jobApi = (JobApi) o;
        return Objects.equals(id, jobApi.id) &&
                Objects.equals(jobName, jobApi.jobName) &&
                Objects.equals(idJobType, jobApi.idJobType) &&
                Objects.equals(typeName, jobApi.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, jobName, idJobType, typeName);
    }

    @Override
    public String toString() {
        return "JobApi{" +
                "id=" + id +
                ", jobName='" + jobName + '\'' +
                ", idJobType=" + idJobType +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
