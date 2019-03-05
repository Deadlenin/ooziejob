package ru.ooziejobstatus.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.ooziejobstatus.entities.JobOozie;
import ru.ooziejobstatus.entities.Report;

public interface JobRepositary extends JpaRepository<JobOozie, Long> {
}

