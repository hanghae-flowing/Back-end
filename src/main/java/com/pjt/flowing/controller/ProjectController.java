package com.pjt.flowing.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pjt.flowing.dto.*;
import com.pjt.flowing.dto.request.*;
import com.pjt.flowing.dto.response.ProjectResponseDto;
import com.pjt.flowing.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("/project/detail") //  프로젝트 조회하기 휴지통제외하고
    public List<ProjectResponseDto> getProject(@RequestBody AuthorizationDto requestDto) throws JsonProcessingException {
        return projectService.getAll(requestDto.getUserId());
    }

    @Transactional
    @PostMapping("/project")    //프로젝트 생성
    public String createProject(@RequestBody ProjectCreateRequestDto projectCreateRequestDto) throws JsonProcessingException {
        return projectService.createProject(projectCreateRequestDto);
    }

    @Transactional
    @PostMapping("/bookmark/{projectId}")   //북마크 생성
    public String checkBookmark(@PathVariable Long projectId , @RequestBody AuthorizationDto authorizationDto) {
        return projectService.checkBookmark(projectId,authorizationDto);
    }

    @PostMapping("/project/delete/{projectId}")    //프로젝트 삭제
    public String deleteProject(@PathVariable Long projectId,@RequestBody AuthorizationDto dto){
        return projectService.deleteProject(projectId, dto);
    }

    @PutMapping("/project/{projectId}")     //프로젝트 수정(파티장만 가능하게 해달랬음)
    public String editProject(@PathVariable Long projectId, ProjectEditRequestDto dto) {
        return projectService.editProject(projectId, dto);
    }

    @GetMapping("/project/{projectId}")   //프로젝트 상세페이지 정보 보내주기
    public String detail(@PathVariable Long projectId){
        return projectService.detail(projectId);
    }

    @PostMapping("/myproject")             //자기가만든 프로젝트 리스트
    public List<ProjectResponseDto> inProject(@RequestBody AuthorizationDto requestDto) {
        return projectService.getAllCreate(requestDto.getUserId());
    }

    @PostMapping("/bookmarked")            //북마크한 프로젝트 리스트
    public List<ProjectResponseDto> getProjectBookmarked(@RequestBody AuthorizationDto requestDto){
        return projectService.getAllBookmarked(requestDto.getUserId());

    }

    @PostMapping("/invitation")  //초대 수락하는 api
    public String accept(@RequestBody AcceptRequestDto acceptRequestDto){
        return projectService.accept(acceptRequestDto);
    }

    @GetMapping("/project/{projectid}/templates")   //모든 템플릿 리스트 불러오기
    public String showTemplates(@PathVariable Long projectid){
        return projectService.showTemplates(projectid);
    }

    @PostMapping("/project/searching")       //검색할 프로젝트명이 포함되어있는 프로젝트불러오기
    public List<ProjectResponseDto> searchProject(@RequestBody ProjectSearchDto requestDto) throws JsonProcessingException {
        return projectService.searchAll(requestDto.getUserId(),requestDto.getText());
    }

    //폴더 생성하기
    @PostMapping("/folder")
    public String createFolder(@RequestBody FolderCreateRequestDto folderCreateRequestDto){
        return projectService.createFolder(folderCreateRequestDto);
    }

    // 폴더에 프로젝트 추가하기
    @PostMapping("/folder/addProject")
    public String addFolder(@RequestBody FolderAddProjectRequestDto requestDto){
        return projectService.addProjectFolder(requestDto);
    }

}
