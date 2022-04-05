package com.pjt.flowing.repository.gapnode;

import com.pjt.flowing.model.gapnode.GapTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GapTableRepository extends JpaRepository<GapTable,Long> {
    List<GapTable> findAllByProject_Id(Long projectId);
    GapTable findByProject_Id(Long projectId);
}
