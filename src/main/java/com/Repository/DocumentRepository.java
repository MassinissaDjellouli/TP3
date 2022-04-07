package com.Repository;

import com.Models.Documents.Documents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Documents,Long> {
}
