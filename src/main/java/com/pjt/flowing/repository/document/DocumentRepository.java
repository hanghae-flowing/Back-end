package com.pjt.flowing.repository.document;

import com.pjt.flowing.model.document.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document,Long> {
    List<Document> findAllByProject_Id(Long projectId);
    Document findByProject_Id(Long projectId);
}
