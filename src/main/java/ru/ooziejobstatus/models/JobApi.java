package ru.ooziejobstatus.models;

import java.util.Objects;

public class JobApi {
    private Long id;
    private String JobName;
    private Integer JobType;

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
        JobName = jobName;
    }

    public Integer getJobType() {
        return JobType;
    }

    public void setJobType(Integer jobType) {
        JobType = jobType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobApi jobApi = (JobApi) o;
        return Objects.equals(id, jobApi.id) &&
                Objects.equals(JobName, jobApi.JobName) &&
                Objects.equals(JobType, jobApi.JobType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, JobName, JobType);
    }
}
