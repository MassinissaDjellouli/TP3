package com.Repository;

import com.Models.Users.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employe,Long> {
}
