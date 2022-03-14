package com.pjt.flowing.repository;

import com.pjt.flowing.dto.ProjectResponseDto;
import com.pjt.flowing.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findProjectsByMember_KakaoId(Long kakaoId);
//    Project findAllByMemberKakaoId(Long kakaoId);

}
