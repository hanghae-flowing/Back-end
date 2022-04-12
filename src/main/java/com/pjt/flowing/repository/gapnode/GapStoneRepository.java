package com.pjt.flowing.repository.gapnode;

import com.pjt.flowing.model.gapnode.GapStone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GapStoneRepository extends JpaRepository<GapStone, Long> {
    List<GapStone> findAllByGapNode_Id(Long gapNodeId);
}
