package com.pjt.flowing.controller;

import com.pjt.flowing.dto.PollingEditDto;
import com.pjt.flowing.dto.response.PollingResponseDto;
import com.pjt.flowing.dto.PollingTestDto;
import com.pjt.flowing.model.PollingTest;
import com.pjt.flowing.repository.PollingRepository;
import com.pjt.flowing.util.ToJsonHelper;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PollingTestController {
    private final PollingRepository pollingRepository;

    @GetMapping("api/test/{textId}") //test api
    public String testt(@PathVariable Long textId){
        PollingTest pollingTest = pollingRepository.findById(textId).orElseThrow(
                ()->new IllegalArgumentException("get error")
        );
        return pollingTest.getText();
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

    @Transactional
    @PostMapping("api/test/text")   //맨처음 생성시, 풀링 x
    public String textt(@RequestBody PollingTestDto dto){
        PollingTest pollingTest = new PollingTest(dto.getText());
        pollingRepository.save(pollingTest);
        ToJsonHelper helper = new ToJsonHelper();
        helper.PutKeyObject("클래스설정테스트","응애에요");
        helper.PutKeyObject("클래스설정테스트2",dto.getText());
        JSONObject obj = new JSONObject();
        obj.put("msg","폴링 생성 테스트");
        obj.put("text",dto.getText());
        obj.put("textId",pollingTest.getId());
        return helper.Result();
    }

    @Transactional
    @PutMapping("api/test/textput/{textId}")
    public String textp(@PathVariable Long textId,@RequestBody PollingEditDto dto){
        PollingTest pollingTest  = pollingRepository.findById(textId).orElseThrow(
                ()->new IllegalArgumentException("edit error")
        );
        pollingTest.update(dto);
        return pollingTest.getText();
    }
}