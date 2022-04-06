package com.pjt.flowing.repository.project;

import com.pjt.flowing.model.project.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember,Long> {
   List<ProjectMember> findAllByMember_Id(Long userId);
   boolean existsByMember_EmailAndProject_Id(String userEmail,Long projectId);
   boolean existsByMember_IdAndProject_Id(Long userId, Long projectId);
   void deleteByMember_IdAndProject_Id(Long userId, Long projectId);
   List<ProjectMember> findAllByProject_Id(Long projectId);


}
