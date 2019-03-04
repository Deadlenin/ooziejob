package ru.ooziejobstatus.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "report_pentaho")
@Cacheable(false)
public class Report {
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
        ReportPath = reportPath;
    }


//  @OneToMany(mappedBy = "report_pentaho",
//          fetch = FetchType.LAZY,
//          cascade = CascadeType.ALL, orphanRemoval = true)
//  private Collection<JobNames> JobNamesList;
//
//  public Collection<JobNames> getJobNamesList() {
//      if (JobNamesList == null)
//          JobNamesList = new ArrayList<>();
//      return JobNamesList;
//  }
//
//  public Report addJobNames(JobNames JobNames) {
//      JobNames.setReport(this);
//      getJobNamesList().add(JobNames);
//      return this;
//  }
//
//  public void setJobNamesList(Collection<JobNames> jobName) {
//      this.JobNamesList = jobName;
//  }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Report that = (Report)o;
//        return Objects.equals(id, that.id) &&
//                Objects.equals(Report, that.Report);
//    }
//
//    @Override
//    public int hashCode() {
//
//        return Objects.hash(id, Report);
//    }
}
