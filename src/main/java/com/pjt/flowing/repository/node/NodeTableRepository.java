package com.pjt.flowing.repository.node;

import com.pjt.flowing.model.node.NodeTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NodeTableRepository extends JpaRepository<NodeTable,Long> {
    List<NodeTable> findAllByProject_Id(Long projectId);
    NodeTable findByProject_Id(Long projectId);
}
