package com.pjt.flowing.controller;

import com.pjt.flowing.dto.request.GapNodeEditRequestDto;
import com.pjt.flowing.dto.request.GapNodeCreateRequestDto;
import com.pjt.flowing.service.GapNodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class GapNodeController {
    private final GapNodeService gapNodeService;
    // 생성
    @PostMapping("/gapTable/{projectId}")
    public String gapTableCreate(@PathVariable Long projectId){
        return gapNodeService.gapTableCreate(projectId);
    }

    @PostMapping("/gapNode")
    public String gapNodeCreate(@RequestBody GapNodeCreateRequestDto gapNodeCreateRequestDto){
        return gapNodeService.gapNodeCreate(gapNodeCreateRequestDto);
    }

    // 변경
    @PutMapping("/gapNode/{gapNodeId}")
    public String gapNodeEdit(@PathVariable Long gapNodeId,@RequestBody GapNodeEditRequestDto gapNodeEditRequestDto){
        return gapNodeService.gapNodeEdit(gapNodeId,gapNodeEditRequestDto);
    }

    // 삭제
    @DeleteMapping("/gapNode/{gapNodeId}")
    public String gapNodeDelete(@PathVariable Long gapNodeId){
        return gapNodeService.gapNodeDelete(gapNodeId);
    }

    // 조회
    @GetMapping("/gapNode/all/{gapTableId}")
    public String getAll(@PathVariable Long gapTableId){
        return gapNodeService.showAll(gapTableId);
    }
}
