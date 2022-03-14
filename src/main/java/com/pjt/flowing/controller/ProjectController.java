package com.pjt.flowing.controller;

<<<<<<< refs/remotes/origin/develop
import com.fasterxml.jackson.core.JsonProcessingException;
import com.pjt.flowing.dto.AuthorizationDto;
import com.pjt.flowing.dto.MsgResponseDto;
import com.pjt.flowing.dto.PjCreateRequestDto;
import com.pjt.flowing.model.Member;
import com.pjt.flowing.model.Project;
import com.pjt.flowing.repository.MemberRepository;
import com.pjt.flowing.repository.ProjectRepository;
import com.pjt.flowing.security.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

=======
import com.pjt.flowing.dto.ProjectResponseDto;
import com.pjt.flowing.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
>>>>>>> Auto stash before rebase of "origin/develop"
@RequiredArgsConstructor
@RestController
public class ProjectController {
    private final ProjectService projectService;
    @GetMapping("/api/post")
    public List<ProjectResponseDto> getProject(){
        List<ProjectResponseDto> response = projectService.getAll();
        return response;
    }

    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;
    private final Authorization authorization;

    @PostMapping("/api/project/create")
    public MsgResponseDto createProject(@RequestBody PjCreateRequestDto pjCreateRequestDto) throws JsonProcessingException {
        AuthorizationDto authorizationDto = new AuthorizationDto(pjCreateRequestDto.getKakaoId(), pjCreateRequestDto.getAccessToken());
        MsgResponseDto msgResponseDto = new MsgResponseDto();
        if (authorization.getKakaoId(authorizationDto) == 0){
            msgResponseDto.setMsg("kakaoId가 다르거나 유효하지 않은 토큰입니다.");
            return msgResponseDto;
        }

        Member member = memberRepository.findByKakaoId(pjCreateRequestDto.getKakaoId()).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        Project project = new Project(
                pjCreateRequestDto.getProjectName(),
                pjCreateRequestDto.getObjectId(),
                member,
                pjCreateRequestDto.getThumbNailNum()
        );
        projectRepository.save(project);
        msgResponseDto.setMsg("프로젝트 생성 완료");
        return msgResponseDto;
    }
}
