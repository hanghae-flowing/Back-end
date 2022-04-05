package com.pjt.flowing.repository.node;

import com.pjt.flowing.model.node.Node;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NodeRepository extends JpaRepository<Node,Long> {
    List<Node> findAllByNodeTable_Id(Long nodeTableId);
}
