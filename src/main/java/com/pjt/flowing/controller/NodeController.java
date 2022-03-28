package com.pjt.flowing.controller;

import com.pjt.flowing.dto.request.NodeCreateRequestDto;
import com.pjt.flowing.dto.request.NodeEditRequestDto;
import com.pjt.flowing.dto.request.NodePinRequestDto;
import com.pjt.flowing.service.NodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class NodeController {
    private final NodeService nodeService;

    @PostMapping("/nodeTable/{projectId}")  //노드 테이블 생성
    public String nodeTableCreate(@PathVariable Long projectId){
        return nodeService.nodeTableCreate(projectId);
    }

    @PostMapping("/node")     //노드 생성
    public String nodeCreate(@RequestBody NodeCreateRequestDto nodeCreateRequestDto){
        return nodeService.nodeCreate(nodeCreateRequestDto);
    }

    @PostMapping("/node/pin")        //노드 하이라이트
    public String pin(@RequestBody NodePinRequestDto nodePinRequestDto){
        return nodeService.pin(nodePinRequestDto);
    }

    @DeleteMapping("/node/{nodeId}")      //개별 노드 삭제
    public String nodeDelete(@PathVariable Long nodeId){
        return nodeService.nodeDelete(nodeId);
    }

    @PutMapping("/node/{nodeId}")       //개별 노드 수정
    public String nodeEdit(@PathVariable Long nodeId,@RequestBody NodeEditRequestDto nodeEditRequestDto){
        return nodeService.nodeEdit(nodeId,nodeEditRequestDto);
    }

    @GetMapping("/node/all/{nodeTableId}") // 노드 템플릿 한개의 노드 전체보기
    public String getAll(@PathVariable Long nodeTableId){
        return nodeService.showAll(nodeTableId);
    }

    @GetMapping("/node/{nodeId}")   //노드 하나 보기
    public String getOne(@PathVariable Long nodeId) {return nodeService.showOne(nodeId);}

}
