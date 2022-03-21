package com.pjt.flowing.controller;

import com.pjt.flowing.dto.NodeCreateRequestDto;
import com.pjt.flowing.dto.NodePinRequestDto;
import com.pjt.flowing.service.NodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class NodeController {
    private final NodeService nodeService;

    @PostMapping("api/node/create")
    public String nodeCreate(@RequestBody NodeCreateRequestDto nodeCreateRequestDto){
        return nodeService.nodeCreate(nodeCreateRequestDto);
    }

    @PostMapping("api/node/pin")
    public String pin(@RequestBody NodePinRequestDto nodePinRequestDto){
        return nodeService.pin(nodePinRequestDto);
    }

}
