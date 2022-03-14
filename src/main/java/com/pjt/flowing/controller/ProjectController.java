package com.pjt.flowing.controller;

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

@RequiredArgsConstructor
@RestController
public class ProjectController {

    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;
    private final Authorization authorization;

    @PostMapping("/api/project/create")
    public MsgResponseDto createProject(@RequestBody PjCreateRequestDto pjCreateRequestDto) throws JsonProcessingException {
        AuthorizationDto authorizationDto = new AuthorizationDto(pjCreateRequestDto.getKakaoId(), pjCreateRequestDto.getAccessToken());
        MsgResponseDto msgResponseDto = new MsgResponseDto();
        if (Objects.equals(authorization.getKakaoId(authorizationDto), "kakaoId가 다르거나 유효하지 않은 토큰입니다.")){
            msgResponseDto.setMsg("kakaoId가 다르거나 유효하지 않은 토큰입니다.");
            return msgResponseDto;
        }

        Member member = memberRepository.findByKakaoId(pjCreateRequestDto.getKakaoId()).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        Project project = new Project(
                pjCreateRequestDto.getProjectName(),
                member,
                pjCreateRequestDto.getThumbnailNum()
        );
        projectRepository.save(project);
        msgResponseDto.setMsg("프로젝트 생성 완료");
        return msgResponseDto;
    }
}
