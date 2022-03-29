package com.pjt.flowing.repository.swot;

import com.pjt.flowing.model.swot.OpportunityTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpportunityRepository extends JpaRepository<OpportunityTable, Long> {
}
