package com.pjt.flowing.repository;


import com.pjt.flowing.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByMemberId(Long userId);
//    Project findAllByMemberKakaoId(Long kakaoId);

}
