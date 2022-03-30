package com.pjt.flowing.model.repository;


import com.pjt.flowing.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByMember_IdOrderByModifiedAtDesc(Long userId);
    List<Project> findFirst4ByMember_IdOrderByModifiedAtDesc(Long userId);

}
