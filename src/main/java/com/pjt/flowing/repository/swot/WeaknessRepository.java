package com.pjt.flowing.repository.swot;

import com.pjt.flowing.model.swot.WeaknessTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeaknessRepository extends JpaRepository<WeaknessTable, Long> {
    List<WeaknessTable> findAllBySwot_Id(Long swotId);
}
