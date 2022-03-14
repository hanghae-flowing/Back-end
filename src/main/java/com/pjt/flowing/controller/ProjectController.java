package com.pjt.flowing.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pjt.flowing.dto.AuthorizationDto;
import com.pjt.flowing.dto.MsgResponseDto;
import com.pjt.flowing.dto.PjCreateRequestDto;
import com.pjt.flowing.dto.ProjectResponseDto;
import com.pjt.flowing.model.Member;
import com.pjt.flowing.model.Project;
import com.pjt.flowing.repository.MemberRepository;
import com.pjt.flowing.repository.ProjectRepository;
import com.pjt.flowing.security.Authorization;
import com.pjt.flowing.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProjectController {
    private final ProjectService projectService;
    private final MemberRepository memberRepository;
    private final Authorization authorization;
    private final ProjectRepository projectRepository;

    @PostMapping("api/project/readAll")
    public List<ProjectResponseDto> getProject(@RequestBody AuthorizationDto requestDto) throws JsonProcessingException {
        if(authorization.getKakaoId(requestDto)==0){
            System.out.println("인가x");
        }
        List<ProjectResponseDto> response = projectService.getAll(requestDto.getKakaoId());
        //수정필요
        return response;
    }

    //api/project/read 만들어야됨

    @PostMapping("/api/project/create")
    public MsgResponseDto createProject(@RequestBody PjCreateRequestDto pjCreateRequestDto) throws JsonProcessingException {
        AuthorizationDto authorizationDto = new AuthorizationDto(pjCreateRequestDto.getKakaoId(), pjCreateRequestDto.getAccessToken());
        MsgResponseDto msgResponseDto = new MsgResponseDto();
        System.out.println("여기");
        if (authorization.getKakaoId(authorizationDto) == 0){
            msgResponseDto.setMsg("kakaoId가 다르거나 유효하지 않은 토큰입니다.");
            return msgResponseDto;
        }
        System.out.println("여기22");
        Member member = memberRepository.findByKakaoId(pjCreateRequestDto.getKakaoId()).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        System.out.println("여기33");
//        member.getKakaoId();
        Project project = new Project(
                pjCreateRequestDto.getProjectName(),
                pjCreateRequestDto.getObjectId(),
                member, //여기 리팩토링 해야됨
                pjCreateRequestDto.getThumbNailNum()
        );
        System.out.println("여기44");
        projectRepository.save(project);
        msgResponseDto.setMsg("프로젝트 생성 완료");
        return msgResponseDto;
    }
}
