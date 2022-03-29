package com.pjt.flowing.repository.swot;

import com.pjt.flowing.model.swot.ThreatTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThreatRepository extends JpaRepository<ThreatTable, Long> {
}
