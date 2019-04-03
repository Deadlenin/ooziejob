package ru.ooziejobstatus.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "job_type")
@Cacheable(false)
public class JobType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

    @Column(name = "type_name", unique = true, nullable = false)
    private String typeName;

    public Long getId() {
        return id;
    }

//    @OneToOne(mappedBy = "jobType")
//    private JobOozie jobOozie;

    public void setId(Long id) {
        this.id = id;
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
        JobType jobType = (JobType) o;
        return Objects.equals(id, jobType.id) &&
                Objects.equals(typeName, jobType.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeName);
    }
}
