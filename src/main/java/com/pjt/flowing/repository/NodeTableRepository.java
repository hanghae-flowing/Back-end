package com.pjt.flowing.repository;

import com.pjt.flowing.model.NodeTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NodeTableRepository extends JpaRepository<NodeTable,Long> {
    List<NodeTable> findAllByProject_Id(Long projectId);
}
