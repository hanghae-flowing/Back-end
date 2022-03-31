package com.pjt.flowing.repository;

import com.pjt.flowing.model.Node;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NodeRepository extends JpaRepository<Node,Long> {
    List<Node> findAllByNodeTable_Id(Long nodeTableId);
}
