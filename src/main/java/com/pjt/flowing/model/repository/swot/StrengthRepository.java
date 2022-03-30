package com.pjt.flowing.model.repository.swot;

import com.pjt.flowing.model.swot.StrengthTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StrengthRepository extends JpaRepository<StrengthTable, Long> {
    List<StrengthTable> findAllBySwot_Id(Long swotId);
}
