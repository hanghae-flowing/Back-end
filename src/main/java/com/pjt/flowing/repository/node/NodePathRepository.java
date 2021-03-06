package com.pjt.flowing.repository.node;

import com.pjt.flowing.model.node.NodePath;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NodePathRepository extends JpaRepository<NodePath, Long> {
    List<NodePath> findAllByNodeTable_Id(Long nodeTableId);
    void deleteAllByChildNode(Long childNode);
    void deleteAllByParentNode(Long parentNode);
}
