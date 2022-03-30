package com.pjt.flowing.model.repository;

import com.pjt.flowing.model.PollingTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollingRepository extends JpaRepository<PollingTest, Long> {
}
