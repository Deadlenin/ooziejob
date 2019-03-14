package ru.ooziejobstatus.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ooziejobstatus.entities.Report;

public interface ReportRepository  extends JpaRepository<Report, Long> {
}
