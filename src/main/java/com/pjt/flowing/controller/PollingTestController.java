package com.pjt.flowing.controller;

import com.pjt.flowing.dto.response.PollingResponseDto;
import com.pjt.flowing.dto.PollingTestDto;
import com.pjt.flowing.model.PollingTest;
import com.pjt.flowing.repository.PollingRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class PollingTestController {
    private final PollingRepository pollingRepository;

    @GetMapping("api/test") //test api
    public String testt(){
        JSONObject obj = new JSONObject();
        obj.put("msg","응애응애 폴링테스트에용");
        String id = UUID.randomUUID().toString();
        obj.put("랜덤이에용",id);
        System.out.println("uuid test"+id);
        return obj.toString();
    }

    @GetMapping("api/test/showall")
    public String textshow(){
        List<PollingTest> pollingTestList = pollingRepository.findAll();
        List<PollingResponseDto> pollingResponseDtoList = new ArrayList<>();
        for(PollingTest pollingTest : pollingTestList){
            PollingResponseDto pollingResponseDto = new PollingResponseDto(pollingTest.getId(),
                    pollingTest.getText());
            pollingResponseDtoList.add(pollingResponseDto);
        }
        JSONArray jsonArray = new JSONArray(pollingResponseDtoList);
        return jsonArray.toString();
    }

    @PostMapping("api/test/text")   //맨처음 생성시, 풀링 x
    public String textt(PollingTestDto dto){
        JSONObject obj = new JSONObject();
        obj.put("msg","pollingtest");
        PollingTest pollingTest = new PollingTest(dto.getText());
        pollingRepository.save(pollingTest);
        obj.put("text",dto.getText());
        return obj.toString();
    }

    @PutMapping("api/test/textput")
    public String textp(@RequestBody PollingTestDto dto){
        JSONObject obj = new JSONObject();
        obj.put("msg","polling 수정");
        return obj.toString();
    }
}
