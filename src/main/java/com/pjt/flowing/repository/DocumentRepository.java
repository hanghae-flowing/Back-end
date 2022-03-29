package com.pjt.flowing.repository;

import com.pjt.flowing.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document,Long> {
}
