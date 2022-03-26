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

    @PostMapping("/node")     //노드 생성
    public String nodeCreate(@RequestBody NodeCreateRequestDto nodeCreateRequestDto){
        return nodeService.nodeCreate(nodeCreateRequestDto);
    }

    @PostMapping("/node/pin")        //노드 하이라이트
    public String pin(@RequestBody NodePinRequestDto nodePinRequestDto){
        return nodeService.pin(nodePinRequestDto);
    }

    @DeleteMapping("/node/{id}")      //노드 삭제
    public String nodeDelete(@PathVariable Long id){
        return nodeService.nodeDelete(id);
    }

    @PutMapping("/node/{id}")       //노드 수정
    public String nodeEdit(@PathVariable Long id,@RequestBody NodeEditRequestDto nodeEditRequestDto){
        return nodeService.nodeEdit(id,nodeEditRequestDto);
    }

    @GetMapping("/node/{projectId}") // 노드 전체보기
    public String getAll(@PathVariable Long projectId){
        return nodeService.showAll(projectId);
    }

    @GetMapping("/node/{nodeId}")
    public String getOne(@PathVariable Long nodeId) {return nodeService.showOne(nodeId);}

}
