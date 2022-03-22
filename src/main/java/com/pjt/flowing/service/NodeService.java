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
                .isChecked(nodeCreateRequestDto.getIsChecked())
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
        Node node = nodeRepository.findById(nodePinRequestDto.getNodeId()).orElseThrow(
                ()-> new IllegalArgumentException("ㅁ?ㄹ")
        );

        if(node.getIsChecked()==0){
            node.setIsChecked(1);
            obj.put("msg","체크 완료");
        }
        else{
            node.setIsChecked(0);
            obj.put("msg","체크 해제");
        }
        return obj.toString();
    }

}
