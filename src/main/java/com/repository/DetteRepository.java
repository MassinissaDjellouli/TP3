package com.repository;

import com.models.Dette;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetteRepository extends JpaRepository<Dette,Integer> {
}
