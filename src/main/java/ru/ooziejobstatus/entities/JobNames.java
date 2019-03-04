package ru.ooziejobstatus.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "job_oozie")
@Cacheable(false)
public class JobNames {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

    @Basic
    @Column(name = "job_name")
    private String jobName;

    @ManyToOne
    @JoinColumn(name = "id_report_pentaho", referencedColumnName = "id")
    private ReportName report;


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

    public ReportName getReport() {
        return report;
    }

    public void setReport(ReportName report) {
        this.report = report;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        JobNames jobNames = (JobNames)o;
//        return Objects.equals(id, jobNames.id) &&
//                Objects.equals(jobName, jobNames.jobName) &&
//                Objects.equals(report, jobNames.report);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, jobName, report);
//    }
//
//    @Override
//    public String toString() {
//        return "JobNames{" +
//                "id=" + id +
//                ", jobName='" + jobName + '\'' +
//                ", report=" + report +
//                '}';
//    }
}
