package com.pjt.flowing.service;

import com.pjt.flowing.dto.request.SWOTRequestDto;
import com.pjt.flowing.dto.response.SWOTResponseDto;
import com.pjt.flowing.model.Project;
import com.pjt.flowing.model.swot.*;
import com.pjt.flowing.repository.ProjectRepository;
import com.pjt.flowing.repository.swot.*;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SWOTService {

    private final SWOTRepository swotRepository;
    private final ProjectRepository projectRepository;
    private final StrengthRepository strengthRepository;
    private final WeaknessRepository weaknessRepository;
    private final OpportunityRepository opportunityRepository;
    private final ThreatRepository threatRepository;

    @Transactional
    public String swotCreate(Long projectId) {
        JSONObject obj = new JSONObject();
        Project project = projectRepository.findById(projectId).orElseThrow(
                ()-> new IllegalArgumentException("project Id error")
        );
        SWOT swot = new SWOT(project);
        swotRepository.save(swot);
        obj.put("swotId",swot.getId()); //responseDto로 보내주세용

        List<SWOTResponseDto> strengthList = new ArrayList<>();
        List<SWOTResponseDto> weaknessList = new ArrayList<>();
        List<SWOTResponseDto> opportunityList = new ArrayList<>();
        List<SWOTResponseDto> threatList = new ArrayList<>();
        for(int i=0; i<5; i++) {

            StrengthTable strengthTable = new StrengthTable("내부 강점을 적어주세요", swot);
            WeaknessTable weaknessTable = new WeaknessTable("내부 약점을 적어주세요", swot);
            OpportunityTable opportunityTable = new OpportunityTable("외부 기회를 적어주세요", swot);
            ThreatTable threatTable = new ThreatTable("외부 위협을 적어주세요", swot);

            strengthRepository.save(strengthTable);
            weaknessRepository.save(weaknessTable);
            opportunityRepository.save(opportunityTable);
            threatRepository.save(threatTable);

            SWOTResponseDto strengthDto = new SWOTResponseDto(strengthTable.getId(), strengthTable.getText());
            SWOTResponseDto weaknessDto = new SWOTResponseDto(weaknessTable.getId(), weaknessTable.getText());
            SWOTResponseDto opportunityDto = new SWOTResponseDto(opportunityTable.getId(), opportunityTable.getText());
            SWOTResponseDto threatDto = new SWOTResponseDto(threatTable.getId(), threatTable.getText());

            strengthList.add(strengthDto);
            weaknessList.add(weaknessDto);
            opportunityList.add(opportunityDto);
            threatList.add(threatDto);
        }
        JSONArray array1 = new JSONArray(strengthList);
        JSONArray array2 = new JSONArray(weaknessList);
        JSONArray array3 = new JSONArray(opportunityList);
        JSONArray array4 = new JSONArray(threatList);
        obj.put("strengthList",array1);
        obj.put("weaknessList",array2);
        obj.put("opportunityList",array3);
        obj.put("threatList",array4);

        return obj.toString();
    }

    @Transactional
    public String showAll(Long swotId) {
        List<StrengthTable> strengthList = strengthRepository.findAllBySwot_Id(swotId);
        List<WeaknessTable> weaknessList = weaknessRepository.findAllBySwot_Id(swotId);
        List<OpportunityTable> opportunityList = opportunityRepository.findAllBySwot_Id(swotId);
        List<ThreatTable> threatList = threatRepository.findAllBySwot_Id(swotId);

        List<SWOTResponseDto> strengthDtoList = new ArrayList<>();
        List<SWOTResponseDto> weaknessDtoList = new ArrayList<>();
        List<SWOTResponseDto> opportunityDtoList = new ArrayList<>();
        List<SWOTResponseDto> threatDtoList = new ArrayList<>();

        for (StrengthTable strengthTable : strengthList) {
            SWOTResponseDto swotResponseDto = new SWOTResponseDto(strengthTable.getId(), strengthTable.getText());
            strengthDtoList.add(swotResponseDto);
        }
        for (WeaknessTable weaknessTable : weaknessList) {
            SWOTResponseDto swotResponseDto = new SWOTResponseDto(weaknessTable.getId(), weaknessTable.getText());
            weaknessDtoList.add(swotResponseDto);
        }
        for (OpportunityTable opportunityTable : opportunityList) {
            SWOTResponseDto swotResponseDto = new SWOTResponseDto(opportunityTable.getId(), opportunityTable.getText());
            opportunityDtoList.add(swotResponseDto);
        }
        for (ThreatTable threatTable : threatList) {
            SWOTResponseDto swotResponseDto = new SWOTResponseDto(threatTable.getId(), threatTable.getText());
            threatDtoList.add(swotResponseDto);
        }
        JSONObject obj = new JSONObject();
        JSONArray array1 = new JSONArray(strengthDtoList);
        JSONArray array2 = new JSONArray(weaknessDtoList);
        JSONArray array3 = new JSONArray(opportunityDtoList);
        JSONArray array4 = new JSONArray(threatDtoList);

        obj.append("strengthDtoList", array1);
        obj.append("weaknessDtoList", array2);
        obj.append("opportunityDtoList", array3);
        obj.append("threatDtoList", array4);

        return obj.toString();
    }

    @Transactional
    public String StrengthEdit (Long strengthId, SWOTRequestDto requestDto) {
        StrengthTable strengthTable = strengthRepository.findById(strengthId).orElseThrow(
                () -> new IllegalArgumentException("not found strengthId")
        );
        strengthTable.strengthUpdate(requestDto);
        SWOTResponseDto swotResponseDto = new SWOTResponseDto(strengthId, requestDto.getText());
        JSONObject obj = new JSONObject(swotResponseDto);

        return obj.toString();
    }

    @Transactional
    public String WeaknessEdit(Long weaknessId, SWOTRequestDto requestDto) {
        WeaknessTable weaknessTable = weaknessRepository.findById(weaknessId).orElseThrow(
                () -> new IllegalArgumentException("not found weaknessId")
        );
        weaknessTable.weaknessUpdate(requestDto);
        SWOTResponseDto swotResponseDto = new SWOTResponseDto(weaknessId, requestDto.getText());
        JSONObject obj = new JSONObject(swotResponseDto);

        return obj.toString();
    }

    @Transactional
    public String OpportunityEdit(Long opportunityId, SWOTRequestDto requestDto) {
        OpportunityTable opportunityTable = opportunityRepository.findById(opportunityId).orElseThrow(
                () -> new IllegalArgumentException("not found opportunityId")
        );
        opportunityTable.opportunityUpdate(requestDto);
        SWOTResponseDto swotResponseDto = new SWOTResponseDto(opportunityId, requestDto.getText());
        JSONObject obj = new JSONObject(swotResponseDto);

        return obj.toString();
    }

    @Transactional
    public String ThreatEdit(Long threatId, SWOTRequestDto requestDto) {
        ThreatTable threatTable = threatRepository.findById(threatId).orElseThrow(
                () -> new IllegalArgumentException("not found threatId")
        );
        threatTable.threatUpdate(requestDto);
        SWOTResponseDto swotResponseDto = new SWOTResponseDto(threatId, requestDto.getText());
        JSONObject obj = new JSONObject(swotResponseDto);

        return obj.toString();
    }
}
