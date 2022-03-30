package com.pjt.flowing.repository;

import com.pjt.flowing.model.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember,Long> {
   List<ProjectMember> findAllByMember_Id(Long userId);
   boolean existsByMember_EmailAndProject_Id(String userEmail,Long projectId);



}
