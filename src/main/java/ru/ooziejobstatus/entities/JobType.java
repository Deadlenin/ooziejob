package ru.ooziejobstatus.entities;

import javax.persistence.*;

@Entity
@Table(name = "job_type")
@Cacheable(false)
public class JobType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

}
