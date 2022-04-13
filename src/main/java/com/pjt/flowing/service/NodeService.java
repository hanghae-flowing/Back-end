package com.pjt.flowing.service;

import com.pjt.flowing.dto.request.node.NodeCreateRequestDto;
import com.pjt.flowing.dto.request.node.NodeEditRequestDto;
import com.pjt.flowing.dto.request.node.NodePathRequestDto;
import com.pjt.flowing.dto.request.node.NodePinRequestDto;
import com.pjt.flowing.dto.response.node.NodePathResponseDto;
import com.pjt.flowing.dto.response.node.NodeResponseDto;
import com.pjt.flowing.model.node.Node;
import com.pjt.flowing.model.node.NodePath;
import com.pjt.flowing.model.node.NodeTable;
import com.pjt.flowing.model.project.Project;
import com.pjt.flowing.repository.node.NodePathRepository;
import com.pjt.flowing.repository.node.NodeRepository;
import com.pjt.flowing.repository.node.NodeTableRepository;
import com.pjt.flowing.repository.project.ProjectRepository;
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
    private final NodeTableRepository nodeTableRepository;
    private final ProjectRepository projectRepository;
    private final NodePathRepository nodePathRepository;

    @Transactional
    public String nodeCreate(NodeCreateRequestDto nodeCreateRequestDto){
        JSONObject obj = new JSONObject();
        NodeTable nodeTable = nodeTableRepository.findById(nodeCreateRequestDto.getNodeTableId()).orElseThrow(
                ()->new IllegalArgumentException("func/ nodeCreate/ nodeTable Id")
        );
        Node node = Node.builder()
                .height(nodeCreateRequestDto.getHeight())
                .isChecked(nodeCreateRequestDto.getIsChecked())
                .radius(nodeCreateRequestDto.getRadius())
                .text(nodeCreateRequestDto.getText())
                .width(nodeCreateRequestDto.getWidth())
                .xval(nodeCreateRequestDto.getXval())
                .yval(nodeCreateRequestDto.getYval())
                .nodeTable(nodeTable)
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
                .nodeTableId(nodeTable.getId())
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
                ()-> new IllegalArgumentException("func/ pin/ node Id")
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
        nodePathRepository.deleteAllByChildNode(id);
        nodePathRepository.deleteAllByParentNode(id);
        JSONObject obj = new JSONObject();
        obj.put("msg","노드 삭제");
        return obj.toString();
    }

    @Transactional
    public String nodeEdit(Long id, NodeEditRequestDto nodeEditRequestDto){
        Node node = nodeRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("func/ nodeEdit/ edit error")
        );
        node.update(nodeEditRequestDto);
        JSONObject obj = new JSONObject();
        obj.put("msg","수정 완료");
        return obj.toString();
    }

    public String showAll(Long nodeTableId){
        List<Node> nodeList = nodeRepository.findAllByNodeTable_Id(nodeTableId);
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
                    .nodeTableId(nodeTableId)
                    .nodeId(node.getId())
                    .build();
            nodeResponseDtoList.add(nodeResponseDto);
        }
        JSONArray jsonArray = new JSONArray(nodeResponseDtoList);
        return jsonArray.toString();
    }

    public String showOne(Long nodeId){
        Node node = nodeRepository.findById(nodeId).orElseThrow(
                ()->new IllegalArgumentException("func/ showOne/ node showone error")
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

    @Transactional  //여기에 template default 값 넣어줘야함
    public Long nodeTableCreate(Long projectId){
        Project project = projectRepository.findById(projectId).orElseThrow(
                ()-> new IllegalArgumentException("func/ nodeTableCreate/ project Id error")
        );
        NodeTable nodeTable = new NodeTable(project);
        nodeTableRepository.save(nodeTable);
        JSONObject obj = new JSONObject();
        obj.put("nodeTableId",nodeTable.getId());
        return nodeTable.getId();
    }

    @Transactional
    public String nodeTableDelete(Long nodeTableId){
        nodeTableRepository.deleteById(nodeTableId);
        JSONObject obj = new JSONObject();
        obj.put("msg","노드 테이블 삭제");
        return obj.toString();
    }

    @Transactional
    public String nodeConnect(NodePathRequestDto nodePathRequestDto) {
        NodeTable nodeTable = nodeTableRepository.findById(nodePathRequestDto.getNodeTableId()).orElseThrow(
                () -> new IllegalArgumentException("func/ nodeConnect/ nodeTableId")
        );
        NodePath nodePath = new NodePath(
                nodePathRequestDto.getParentNode(),
                nodePathRequestDto.getChildNode(),
                nodeTable
        );
        nodePathRepository.save(nodePath);
        JSONObject obj = new JSONObject();
        obj.put("parentNode", nodePathRequestDto.getParentNode());
        obj.put("childNode", nodePathRequestDto.getChildNode());
        return obj.toString();
    }

    @Transactional
    public String NodePathFindAll(Long nodeTableId) {
        List<NodePath> nodePathList = nodePathRepository.findAllByNodeTable_Id(nodeTableId);
        List<NodePathResponseDto> nodePathResponseDtoList = new ArrayList<>();
        for (NodePath nodePath : nodePathList) {
            NodePathResponseDto nodePathResponseDto = new NodePathResponseDto(
                    nodePath.getParentNode(),
                    nodePath.getChildNode()
            );
            nodePathResponseDtoList.add(nodePathResponseDto);
        }
        JSONArray array = new JSONArray(nodePathResponseDtoList);
        return array.toString();
    }
}
