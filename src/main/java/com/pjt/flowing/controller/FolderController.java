package com.pjt.flowing.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pjt.flowing.dto.request.FolderAddProjectRequestDto;
import com.pjt.flowing.dto.request.FolderCreateRequestDto;
import com.pjt.flowing.dto.request.FolderDeleteRequestDto;
import com.pjt.flowing.dto.response.FolderTableResponseDto;
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

    // 폴더 삭제하기
    @DeleteMapping("/folder/{userId}")
    public String deleteFolder(@PathVariable Long userId, @RequestBody FolderDeleteRequestDto requestDto){
        return folderService.deleteFolder(requestDto);
    }

}
