package com.repository;

import com.models.users.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {
    @Query("select c from Client c left join fetch c.emprunts")
    public Optional<Client> findByIdWithEmprunts(Integer integer);
}
