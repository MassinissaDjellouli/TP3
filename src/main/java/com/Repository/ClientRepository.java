package com.Repository;

import com.Models.Documents.Documents;
import com.Models.Documents.Livre;
import com.Models.Documents.Media;
import com.Models.Users.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ClientRepository extends JpaRepository<Client,Long> {

}
