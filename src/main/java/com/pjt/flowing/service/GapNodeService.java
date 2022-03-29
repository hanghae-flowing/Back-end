package com.pjt.flowing.service;

import com.pjt.flowing.dto.request.GapNodeEditRequestDto;
import com.pjt.flowing.dto.request.GapNodeCreateRequestDto;
import com.pjt.flowing.dto.response.GapNodeResponseDto;
import com.pjt.flowing.model.GapNode;
import com.pjt.flowing.model.GapTable;
import com.pjt.flowing.model.Project;
import com.pjt.flowing.repository.GapNodeRepository;
import com.pjt.flowing.repository.GapTableRepository;
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
public class GapNodeService {

    private final GapTableRepository gapTableRepository;
    private final GapNodeRepository gapNodeRepository;
    private final ProjectRepository projectRepository;

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
    public String gapTableCreate(Long projectId){
        Project project = projectRepository.findById(projectId).orElseThrow(
                ()-> new IllegalArgumentException("project Id error")
        );

        GapTable gapTable = new GapTable(project);
        gapTableRepository.save(gapTable);
        JSONObject obj = new JSONObject();
        obj.put("gapTableId",gapTable.getId());
        return obj.toString();

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
}
