package com.pjt.flowing.repository.project;


import com.pjt.flowing.model.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByMember_IdAndTrashOrderByModifiedAtDesc(Long userId, boolean trash);
    boolean existsByMember_IdAndId(Long userId, Long projectId);
    Project findByMember_IdAndId(Long userId, Long projectId);
    void deleteAllByMember_IdAndTrash(Long userId, boolean trash);
    }
