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
    // 갭노드 테이블 생성
    //levelDown 나중에 다시 바꿔야함
    @PostMapping("/gapTable/{projectId}")
//    public String gapTableCreate(@PathVariable Long projectId){
    public Long gapTableCreate(@PathVariable Long projectId){
        return gapNodeService.gapTableCreate(projectId);
    }

    // 갭 노드 테이블 삭제
    @DeleteMapping("/gapTable/{gapTableId}")
    public String gapTableDelete(@PathVariable Long gapTableId){
        return gapNodeService.gapTableDelete(gapTableId);
    }

    // 갭노드 생성
    @PostMapping("/gapNode")
    public String gapNodeCreate(@RequestBody GapNodeCreateRequestDto gapNodeCreateRequestDto){
        return gapNodeService.gapNodeCreate(gapNodeCreateRequestDto);
    }

    // 변경
    @PutMapping("/gapNode/{gapNodeId}") // 갭 노드 변경
    public String gapNodeEdit(@PathVariable Long gapNodeId,@RequestBody GapNodeEditRequestDto gapNodeEditRequestDto){
        return gapNodeService.gapNodeEdit(gapNodeId,gapNodeEditRequestDto);
    }

    // 삭제
    @DeleteMapping("/gapNode/{gapNodeId}") //갭 노드 삭제
    public String gapNodeDelete(@PathVariable Long gapNodeId){
        return gapNodeService.gapNodeDelete(gapNodeId);
    }

    // 조회
    @GetMapping("/gapNode/all/{gapTableId}") // 갭 노드전부 조회
    public String getAll(@PathVariable Long gapTableId){
        return gapNodeService.showAll(gapTableId);
    }
}
