package com.pjt.flowing.service;

import com.pjt.flowing.dto.NodeCreateRequestDto;
import com.pjt.flowing.dto.NodePinRequestDto;
import com.pjt.flowing.model.Node;
import com.pjt.flowing.model.Project;
import com.pjt.flowing.repository.NodeRepository;
import com.pjt.flowing.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class NodeService {

    private final NodeRepository nodeRepository;
    private final ProjectRepository projectRepository;

    @Transactional
    public String nodeCreate(NodeCreateRequestDto nodeCreateRequestDto){
        JSONObject obj = new JSONObject();
        Project project = projectRepository.findById(nodeCreateRequestDto.getProjectId()).orElseThrow(
                ()->new IllegalArgumentException("없냐")
        );

        Node node = Node.builder()
                .height(nodeCreateRequestDto.getHeight())
                .isChecked(nodeCreateRequestDto.isChecked())
                .radius(nodeCreateRequestDto.getRadius())
                .text(nodeCreateRequestDto.getText())
                .width(nodeCreateRequestDto.getWidth())
                .xval(nodeCreateRequestDto.getXval())
                .yval(nodeCreateRequestDto.getYval())
                .project(project)
                .build();

        nodeRepository.save(node);
        obj.put("msg","노드생성");
        return obj.toString();
    }

    @Transactional
    public String pin(NodePinRequestDto nodePinRequestDto){
        JSONObject obj = new JSONObject();
        Long projectId = nodePinRequestDto.getProjectId();

        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new IllegalArgumentException("몰라")
        );

        return obj.toString();
    }

}
