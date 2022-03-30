package com.pjt.flowing.model.repository;

import com.pjt.flowing.model.NodePath;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NodePathRepository extends JpaRepository<NodePath, Long> {
    List<NodePath> findAllByNodeTable_Id(Long nodeTableId);
}
