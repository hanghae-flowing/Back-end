package com.pjt.flowing.controller;

import com.pjt.flowing.dto.NodeCreateRequestDto;
import com.pjt.flowing.dto.NodeEditRequestDto;
import com.pjt.flowing.dto.NodePinRequestDto;
import com.pjt.flowing.service.NodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class NodeController {
    private final NodeService nodeService;

    @PostMapping("api/node/create")     //노드 생성
    public String nodeCreate(@RequestBody NodeCreateRequestDto nodeCreateRequestDto){
        return nodeService.nodeCreate(nodeCreateRequestDto);
    }

    @PostMapping("api/node/pin")        //노드 하이라이트
    public String pin(@RequestBody NodePinRequestDto nodePinRequestDto){
        return nodeService.pin(nodePinRequestDto);
    }

    @DeleteMapping("api/node/delete/{id}")      //노드 삭제
    public String nodeDelete(@PathVariable Long id){
        return nodeService.nodeDelete(id);
    }

    @PutMapping("api/node/edit/{id}")       //노드 수정
    public String nodeEdit(@PathVariable Long id,@RequestBody NodeEditRequestDto nodeEditRequestDto){
        return nodeService.nodeEdit(id,nodeEditRequestDto);
    }

    @GetMapping("api/node/showall/{projectId}") // 노드 전체보기
    public String getall(@PathVariable Long projectId){
        return nodeService.showall(projectId);
    }


}
