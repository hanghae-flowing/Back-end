package com.pjt.flowing.service;

import com.pjt.flowing.dto.request.NodeCreateRequestDto;
import com.pjt.flowing.dto.request.NodeEditRequestDto;
import com.pjt.flowing.dto.request.NodePinRequestDto;
import com.pjt.flowing.dto.response.NodeResponseDto;
import com.pjt.flowing.model.Node;
import com.pjt.flowing.model.Project;
import com.pjt.flowing.repository.NodeRepository;
import com.pjt.flowing.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NodeService {

    private final NodeRepository nodeRepository;
    private final ProjectRepository projectRepository;

    @Transactional
    public String nodeCreate(NodeCreateRequestDto nodeCreateRequestDto){
        JSONObject obj = new JSONObject();
        Project project = projectRepository.findById(nodeCreateRequestDto.getProjectId()).orElseThrow(
                ()->new IllegalArgumentException("node Create error")
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

        NodeResponseDto nodeResponseDto = NodeResponseDto.builder()
                .height(node.getHeight())
                .radius(node.getRadius())
                .isChecked(node.getIsChecked())
                .text(node.getText())
                .xval(node.getXval())
                .yval(node.getYval())
                .width(node.getWidth())
                .nodeId(node.getId())
                .projectId(project.getId())
                .build();
        JSONObject obj2 = new JSONObject(nodeResponseDto);
        obj.put("msg","노드 생성");
        obj.put("nodeInfo",obj2);

        return obj.toString();
    }

    @Transactional
    public String pin(NodePinRequestDto nodePinRequestDto){
        JSONObject obj = new JSONObject();
        Node node = nodeRepository.findById(nodePinRequestDto.getNodeId()).orElseThrow(
                ()-> new IllegalArgumentException("pin error")
        );

        if(node.getIsChecked()==0){
            node.setIsChecked(1);
            obj.put("msg","check");
        }
        else{
            node.setIsChecked(0);
            obj.put("msg","cancel");
        }
        return obj.toString();
    }

    @Transactional
    public String nodeDelete(Long id){
        nodeRepository.deleteById(id);
        JSONObject obj = new JSONObject();
        obj.put("msg","노드 삭제");
        return obj.toString();
    }

    @Transactional
    public String nodeEdit(Long id, NodeEditRequestDto nodeEditRequestDto){
        Node node = nodeRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("edit error")
        );
        node.update(nodeEditRequestDto);
        JSONObject obj = new JSONObject();
        obj.put("msg","수정 완료");
        return obj.toString();
    }

    public String showAll(Long projectId){

        List<Node> nodeList = nodeRepository.findAllByProject_Id(projectId);
        List<NodeResponseDto> nodeResponseDtoList = new ArrayList<>();
        for(Node node : nodeList){
            NodeResponseDto nodeResponseDto = NodeResponseDto.builder()
                    .height(node.getHeight())
                    .radius(node.getRadius())
                    .isChecked(node.getIsChecked())
                    .text(node.getText())
                    .xval(node.getXval())
                    .yval(node.getYval())
                    .width(node.getWidth())
                    .projectId(projectId)
                    .nodeId(node.getId())
                    .build();
            nodeResponseDtoList.add(nodeResponseDto);
        }
        JSONArray jsonArray = new JSONArray(nodeResponseDtoList);
        return jsonArray.toString();
    }

    public String showOne(Long nodeId){
        Node node = nodeRepository.findById(nodeId).orElseThrow(
                ()->new IllegalArgumentException("node showone error")
        );
        NodeResponseDto nodeResponseDto = NodeResponseDto.builder()
                .height(node.getHeight())
                .radius(node.getRadius())
                .isChecked(node.getIsChecked())
                .text(node.getText())
                .xval(node.getXval())
                .yval(node.getYval())
                .width(node.getWidth())
                .nodeId(node.getId())
                .build();
        JSONObject obj = new JSONObject(nodeResponseDto);
        return obj.toString();
    }

}
