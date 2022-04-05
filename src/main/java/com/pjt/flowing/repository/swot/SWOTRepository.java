package com.pjt.flowing.repository.swot;

import com.pjt.flowing.model.swot.SWOT;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SWOTRepository extends JpaRepository<SWOT, Long> {
    List<SWOT> findAllByProject_Id(Long projectId);
    SWOT findByProject_Id(Long projectId);
}
