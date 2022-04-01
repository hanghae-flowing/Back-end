package com.pjt.flowing.repository;


import com.pjt.flowing.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByMember_IdOrderByModifiedAtDesc(Long userId);
    List<Project> findAllByMember_IdAndTrashOrderByModifiedAtDesc(Long userId,boolean trash);
    List<Project> findFirst4ByMember_IdOrderByModifiedAtDesc(Long userId);
    boolean existsByMember_IdAndId(Long userId, Long projectId);
    Project findByMember_IdAndId(Long userId, Long projectId);
}
