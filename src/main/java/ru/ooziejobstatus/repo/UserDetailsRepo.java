package ru.ooziejobstatus.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ooziejobstatus.entities.User;

public interface UserDetailsRepo extends JpaRepository<User, String> {
}