package com.pjt.flowing.service;

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

            String s = String.valueOf(i);

            StrengthTable strengthTable = new StrengthTable(s, swot);
            WeaknessTable weaknessTable = new WeaknessTable(s, swot);
            OpportunityTable opportunityTable = new OpportunityTable(s, swot);
            ThreatTable threatTable = new ThreatTable(s, swot);

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
}
