package com.pjt.flowing.repository;

import com.pjt.flowing.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
