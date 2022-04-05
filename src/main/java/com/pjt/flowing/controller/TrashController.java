package com.pjt.flowing.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pjt.flowing.dto.request.AcceptRequestDto;
import com.pjt.flowing.dto.request.ProjectIdDeleteRequestDto;
import com.pjt.flowing.dto.response.FolderTableResponseDto;
import com.pjt.flowing.dto.response.ProjectResponseDto;
import com.pjt.flowing.service.FolderService;
import com.pjt.flowing.service.TrashService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TrashController {

    private final TrashService trashService;
    private final FolderService folderService;

    @PostMapping("project/trash") // 만든 사람은 휴지통으로 보내고 복구하기, member가 작동시 프로젝트멤버에서 삭제
    public String trashProject(@RequestBody AcceptRequestDto requestDto) {
        return trashService.trashProject(requestDto);
    }

    @GetMapping("project/trash/{userId}") // 휴지통에 있는 프로젝트만 보여주기
    public List<ProjectResponseDto> showTrash(@PathVariable Long userId) {
        return trashService.showTrash(userId);
    }

    @DeleteMapping("project/trash/{userId}") // 휴지통 비우기
    public String trashDeleteAll(@PathVariable Long userId) {
        return trashService.trashDeleteAll(userId);
    }

    @PostMapping("project/trash/selection") // 휴지통에서 선택해서 삭제하기
    public String choiceDelete(@RequestBody ProjectIdDeleteRequestDto requestDto) {
        return trashService.choiceDelete(requestDto);
    }

    @PostMapping("project/trash/restore")
    public String restoreProject(@RequestBody ProjectIdDeleteRequestDto requestDto) {
        return trashService.restoreProject(requestDto);
    }

    //휴지통에 있는 폴더만 보여주기
    @GetMapping("/trash/folder/{userId}")
    public List<FolderTableResponseDto> getFolder(@PathVariable Long userId) throws JsonProcessingException {
        return folderService.getTrashFolderAll(userId);
    }


}
