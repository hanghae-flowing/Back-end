package com.pjt.flowing.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pjt.flowing.dto.*;
import com.pjt.flowing.model.Bookmark;
import com.pjt.flowing.model.Member;
import com.pjt.flowing.model.Project;
import com.pjt.flowing.repository.BookmarkRepository;
import com.pjt.flowing.repository.MemberRepository;
import com.pjt.flowing.repository.ProjectRepository;
import com.pjt.flowing.security.Authorization;
import com.pjt.flowing.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProjectController {
    private final ProjectService projectService;
    private final MemberRepository memberRepository;
    private final Authorization authorization;
    private final ProjectRepository projectRepository;
    private final BookmarkRepository bookmarkRepository;

    @PostMapping("api/project/readAll")
    public List<ProjectResponseDto> getProject(@RequestBody AuthorizationDto requestDto) throws JsonProcessingException {
        if(authorization.getKakaoId(requestDto)==0){
            System.out.println("인가x");
        }
        return projectService.getAll(requestDto.getUserId());
    }

    @PostMapping("/api/project/read")
    public List<ProjectResponseDto> getProjectWith4(@RequestBody AuthorizationDto requestDto){

        // Userid로 북마크4개 조회 ->
        return projectService.get4(requestDto.getUserId());
    }

    @PostMapping("/api/project/create")
    public MsgResponseDto createProject(@RequestBody PjCreateRequestDto pjCreateRequestDto) throws JsonProcessingException {
        AuthorizationDto authorizationDto = new AuthorizationDto( pjCreateRequestDto.getAccessToken(),pjCreateRequestDto.getKakaoId(),pjCreateRequestDto.getUserId());
        MsgResponseDto msgResponseDto = new MsgResponseDto();
        if (authorization.getKakaoId(authorizationDto) == 0){
            msgResponseDto.setMsg("kakaoId가 다르거나 유효하지 않은 토큰입니다.");
            return msgResponseDto;
        }
        Member member = memberRepository.findById(pjCreateRequestDto.getUserId()).orElseThrow(
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

    @Transactional
    @PostMapping("/api/bookmark/{projectId}")
    public MsgResponseDto CheckBookmark(@PathVariable Long projectId , @RequestBody AuthorizationDto authorizationDto) {
        System.out.println(projectId);
        boolean check = bookmarkRepository.existsByMember_IdAndProject_Id(authorizationDto.getUserId(), projectId);

        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new IllegalArgumentException("프로젝트가 존재하지 않아요~")
        );
        Member member = memberRepository.findById(authorizationDto.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않아요~")
        );
        MsgResponseDto msgResponseDto = new MsgResponseDto();
        if (!check) {
            Bookmark bookmark = new Bookmark(project, member);
            bookmarkRepository.save(bookmark);
            msgResponseDto.setMsg("북마크 생성!");
            return msgResponseDto;
        }
        else {
            bookmarkRepository.deleteByMember_IdAndProject_Id(authorizationDto.getUserId(), projectId);
            msgResponseDto.setMsg("북마크 취소!");
            return msgResponseDto;
        }
    }

    @DeleteMapping("api/delete/{projectId}")    //프로젝트 삭제
    public String deleteProject(@PathVariable Long projectId,AuthorizationDto dto){
        return projectService.deleteproject(projectId, dto);
    }

    @PutMapping("api/edit/{projectId}")     //프로젝트 수정(파티장만 가능하게 해달랬음)
    public String editProject(@PathVariable Long projectId, ProjectEditRequestDto dto) {
        return projectService.editproject(projectId, dto);
    }

    @GetMapping("api/detail/{projectId}")   //프로젝트 상세페이지 정보 보내주기
    public String detail(@PathVariable Long projectId){
        return projectService.detail(projectId);
    }
}
