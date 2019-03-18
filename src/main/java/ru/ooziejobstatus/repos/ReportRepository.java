package ru.ooziejobstatus.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ooziejobstatus.entities.Report;

@Repository
public interface ReportRepository  extends JpaRepository<Report, Long> {

}
