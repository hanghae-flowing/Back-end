package com.pjt.flowing.repository.swot;

import com.pjt.flowing.model.swot.StrengthTable;
import com.pjt.flowing.model.swot.ThreatTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThreatRepository extends JpaRepository<ThreatTable, Long> {
    List<ThreatTable> findAllBySwot_Id(Long swotId);
}
