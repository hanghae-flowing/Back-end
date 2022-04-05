package com.pjt.flowing.service;

import com.pjt.flowing.dto.request.gapnode.GapNodeEditRequestDto;
import com.pjt.flowing.dto.request.gapnode.GapNodeCreateRequestDto;
import com.pjt.flowing.dto.request.gapnode.GapStoneRequestDto;
import com.pjt.flowing.dto.response.gapnode.GapNodeResponseDto;
import com.pjt.flowing.dto.response.gapnode.GapStoneResponseDto;
import com.pjt.flowing.model.gapnode.GapNode;
import com.pjt.flowing.model.gapnode.GapStone;
import com.pjt.flowing.model.gapnode.GapTable;
import com.pjt.flowing.model.project.Project;
import com.pjt.flowing.repository.gapnode.GapNodeRepository;
import com.pjt.flowing.repository.gapnode.GapStoneRepository;
import com.pjt.flowing.repository.gapnode.GapTableRepository;
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
public class GapNodeService {

    private final GapTableRepository gapTableRepository;
    private final GapNodeRepository gapNodeRepository;
    private final ProjectRepository projectRepository;
    private final GapStoneRepository gapStoneRepository;

    @Transactional
    public String gapNodeCreate(GapNodeCreateRequestDto gapNodeCreateRequestDto){
        JSONObject obj = new JSONObject();
        GapTable gapTable = gapTableRepository.findById(gapNodeCreateRequestDto.getGapTableId()).orElseThrow(
                ()->new IllegalArgumentException("gapTable Id error")
        );

        GapNode gapNode = GapNode.builder()
                .subject(gapNodeCreateRequestDto.getSubject())
                .text(gapNodeCreateRequestDto.getText())
                .targetText(gapNodeCreateRequestDto.getTargetText())
                .gapTable(gapTable)
                .build();

        gapNodeRepository.save(gapNode);

        GapNodeResponseDto gapNodeResponseDto = GapNodeResponseDto.builder()
                .subject(gapNode.getSubject())
                .text(gapNode.getText())
                .targetText(gapNode.getTargetText())
                .gapNodeId(gapNode.getId())
                .gapTableId(gapTable.getId())
                .build();

        JSONObject obj2 = new JSONObject(gapNodeResponseDto);
        obj.put("msg","갭노드 생성");
        obj.put("info",obj2);

        return obj.toString();
    }
    
    @Transactional
    public Long gapTableCreate(Long projectId){
        Project project = projectRepository.findById(projectId).orElseThrow(
                ()-> new IllegalArgumentException("project Id error")
        );

        GapTable gapTable = new GapTable(project);
        gapTableRepository.save(gapTable);
        return gapTable.getId();

    }

    @Transactional
    public String gapNodeEdit(Long id, GapNodeEditRequestDto gapNodeEditRequestDto){
        GapNode gapNode = gapNodeRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("edit error")
        );
        gapNode.update(gapNodeEditRequestDto);
        JSONObject obj = new JSONObject();
        obj.put("msg","수정완료");
        return obj.toString();
    }

    @Transactional
    public String gapNodeDelete(Long id){
        gapNodeRepository.deleteById(id);
        JSONObject obj = new JSONObject();
        obj.put("msg","갭노드 삭제");
        return obj.toString();
    }

    public String showAll(Long gapTableId){
        List<GapNode> gapNodeList = gapNodeRepository.findAllByGapTable_Id(gapTableId);
        List<GapNodeResponseDto> gapNodeResponseDtoList = new ArrayList<>();
        for(GapNode gapNode: gapNodeList){
            GapNodeResponseDto gapNodeResponseDto= GapNodeResponseDto.builder()
                    .subject(gapNode.getSubject())
                    .text(gapNode.getText())
                    .targetText(gapNode.getTargetText())
                    .gapTableId(gapTableId)
                    .gapNodeId(gapNode.getId())
                    .build();
            gapNodeResponseDtoList.add(gapNodeResponseDto);
        }
        JSONArray jsonArray = new JSONArray(gapNodeResponseDtoList);
        return jsonArray.toString();
    }

    @Transactional
    public String gapTableDelete(Long gapTableId){
        gapTableRepository.deleteById(gapTableId);
        JSONObject obj = new JSONObject();
        obj.put("msg","갭분석 테이블 삭제");
        return obj.toString();
    }

    @Transactional
    public String gapStoneCreate(GapStoneRequestDto requestDto) {
        GapNode gapNode = gapNodeRepository.findById(requestDto.getGapNodeId()).orElseThrow(
                () -> new IllegalArgumentException("not exist gapNodeId")
        );
        GapStone gapStone = new GapStone(requestDto.getXval(), requestDto.getText(), gapNode);

        gapStoneRepository.save(gapStone);
        GapStoneResponseDto gapStoneResponseDto = GapStoneResponseDto.builder()
                .xval(gapStone.getXval())
                .text(gapStone.getText())
                .gapNodeId(gapNode.getId())
                .gapStoneId(gapStone.getId())
                .build();
        JSONObject obj2 = new JSONObject(gapStoneResponseDto);
        JSONObject obj = new JSONObject();

        obj.put("GapStoneResponseDto", obj2);
        obj.put("msg", "갭 스톤 생성");

        return obj.toString();
    }

    @Transactional
    public String findAllGapStone(Long gapNodeId) {
        List<GapStone> gapStoneList = gapStoneRepository.findAllByGapNode_Id(gapNodeId);
        List<GapStoneResponseDto> gapStoneResponseDtoList = new ArrayList<>();
        for (GapStone gapStone : gapStoneList) {
            GapStoneResponseDto gapStoneResponseDto = GapStoneResponseDto.builder()
                    .xval(gapStone.getXval())
                    .text(gapStone.getText())
                    .gapStoneId(gapStone.getId())
                    .gapNodeId(gapStone.getGapNode().getId())
                    .build();
            gapStoneResponseDtoList.add(gapStoneResponseDto);
        }
        JSONArray array = new JSONArray(gapStoneResponseDtoList);
        return array.toString();
    }

    @Transactional
    public String gapStoneEdit(Long gapStoneId, GapStoneRequestDto requestDto) {
        GapStone gapStone = gapStoneRepository.findById(gapStoneId).orElseThrow(
                () -> new IllegalArgumentException("not exist gapStoneId")
        );
        gapStone.update(requestDto);
        JSONObject obj = new JSONObject();
        obj.put("msg", "갭스톤 수정 완료!");
        return obj.toString();
    }

    @Transactional
    public String gapStoneDelete(Long gapStoneId) {
        gapStoneRepository.deleteById(gapStoneId);
        JSONObject obj = new JSONObject();
        obj.put("msg", "갭스톤 삭제 완료");
        return obj.toString();
    }
}
