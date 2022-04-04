package com.pjt.flowing.controller;

import com.pjt.flowing.dto.request.FolderAddProjectRequestDto;
import com.pjt.flowing.dto.request.FolderCreateRequestDto;
import com.pjt.flowing.service.FolderService;
import com.pjt.flowing.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
