package com.pjt.flowing.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pjt.flowing.dto.request.FolderAddProjectRequestDto;
import com.pjt.flowing.dto.request.FolderCreateRequestDto;
import com.pjt.flowing.dto.request.FolderDeleteProjectRequestDto;
import com.pjt.flowing.dto.request.FolderRequestDto;
import com.pjt.flowing.dto.response.FolderTableResponseDto;
import com.pjt.flowing.dto.response.ProjectResponseDto;
import com.pjt.flowing.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FolderController {

    private final FolderService folderService;

    //폴더 생성하기
    @PostMapping("/folder")
    public String createFolder(@RequestBody FolderCreateRequestDto folderCreateRequestDto){
        return folderService.createFolder(folderCreateRequestDto);
    }

    // 폴더에 프로젝트 추가하기
    @PostMapping("/folder/addProject")
    public String addFolder(@RequestBody FolderAddProjectRequestDto requestDto){
        return folderService.addProjectFolder(requestDto);
    }

    //가지고 있는 폴더 조회하기
    @GetMapping("/folder/{userId}")
    public List<FolderTableResponseDto> getFolder(@PathVariable Long userId) throws JsonProcessingException {
        return folderService.getFolderAll(userId);
    }

    // 선택한 폴더에 있는 프로젝트 조회하기.
    @PostMapping("/folder/projects")
    public List<ProjectResponseDto> getProject(@RequestBody FolderRequestDto requestDto) throws JsonProcessingException {
        return folderService.getProjectAll(requestDto.getFolderTableId());
    }

    // 폴더 삭제하기(휴지통에서 완전삭제)  안에있는 프로젝트들은 내가만든거면 삭제 남이만든거면 멤버 끊기
    @DeleteMapping("/folder")
    public String deleteFolder( @RequestBody FolderRequestDto requestDto) throws JsonProcessingException{
        return folderService.deleteFolder(requestDto);
    }

    //폴더 휴지통 보내기
    @PostMapping("/folder/trash")
    public String trashFolder( @RequestBody FolderRequestDto requestDto) throws JsonProcessingException{
        return folderService.trashFolder(requestDto);
    }
    //폴더 복구하기.


    // 폴더에 들어있는 프로젝트 삭제하기  //이부분은 더생각해보기
    @DeleteMapping("/folder/project")
    public String deleteFolderProject( @RequestBody FolderDeleteProjectRequestDto requestDto) throws JsonProcessingException{
        return folderService.deleteFolderProject(requestDto);
    }


}
