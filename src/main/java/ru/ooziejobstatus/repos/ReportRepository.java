package ru.ooziejobstatus.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.ooziejobstatus.entities.Report;

public interface ReportRepository  extends JpaRepository<Report, Long> {
}
