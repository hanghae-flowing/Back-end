package com.pjt.flowing.controller;

import com.pjt.flowing.dto.request.gapnode.GapNodeEditRequestDto;
import com.pjt.flowing.dto.request.gapnode.GapNodeCreateRequestDto;
import com.pjt.flowing.dto.request.gapnode.GapStoneRequestDto;
import com.pjt.flowing.service.GapNodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class GapNodeController {
    private final GapNodeService gapNodeService;

    @PostMapping("/gapTable/{projectId}")   //갭노드 테이블 생성
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

    @PostMapping("/gapStone") // 갭 스톤 생성
    public String gapStoneCreate(@RequestBody GapStoneRequestDto requestDto) {
        return gapNodeService.gapStoneCreate(requestDto);
    }

    @GetMapping("/gapStone/{gapNodeId}") // 갭노드에 있는 갭스톤 조회
    public String findAllGapStone(@PathVariable Long gapNodeId) {
        return gapNodeService.findAllGapStone(gapNodeId);
    }

    @PutMapping("/gapStone/{gapStoneId}") // 갭스톤 텍스트 수정
    public String gapStoneEdit(@PathVariable Long gapStoneId, @RequestBody GapStoneRequestDto requestDto) {
        return gapNodeService.gapStoneEdit(gapStoneId, requestDto);
    }

    @DeleteMapping("/gapStone/{gapStoneId}")    //갭스톤 삭제
    public String gapStoneDelete(@PathVariable Long gapStoneId) {
        return gapNodeService.gapStoneDelete(gapStoneId);
    }
}
