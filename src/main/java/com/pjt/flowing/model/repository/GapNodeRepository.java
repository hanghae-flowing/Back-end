package com.pjt.flowing.model.repository;

import com.pjt.flowing.model.GapNode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GapNodeRepository extends JpaRepository<GapNode,Long> {
    List<GapNode> findAllByGapTable_Id(Long gapTableId);
}
