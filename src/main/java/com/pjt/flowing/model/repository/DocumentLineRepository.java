package com.pjt.flowing.model.repository;

import com.pjt.flowing.model.DocumentLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentLineRepository extends JpaRepository<DocumentLine,Long> {
//    List<DocumentLine> findAllByDocument_Id(Long documentId);
    List<DocumentLine> findAllByDocument_IdOrderByIndexNum(Long documentId);
}
