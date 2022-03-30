package com.pjt.flowing.model.repository;

import com.pjt.flowing.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document,Long> {
    List<Document> findAllByProject_Id(Long projectId);
}
