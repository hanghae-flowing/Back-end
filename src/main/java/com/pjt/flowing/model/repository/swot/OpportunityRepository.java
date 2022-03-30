package com.pjt.flowing.model.repository.swot;

import com.pjt.flowing.model.swot.OpportunityTable;
import com.pjt.flowing.model.swot.StrengthTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpportunityRepository extends JpaRepository<OpportunityTable, Long> {
    List<OpportunityTable> findAllBySwot_Id(Long swotId);
}
