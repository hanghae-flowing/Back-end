package com.pjt.flowing.controller;

import com.pjt.flowing.dto.request.SWOTRequestDto;
import com.pjt.flowing.service.SWOTService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class SWOTController {

    private final SWOTService swotService;

    @PostMapping("/swot/{projectId}")
    public String CreateSWOT(@PathVariable Long projectId) {
        return swotService.swotCreate(projectId);
    }

    @GetMapping("/swot/{swotId}")
    public String ShowAll(@PathVariable Long swotId) {
        return swotService.showAll(swotId);
    }

    @PutMapping("/swot/strength/{strengthId}")
    public String strengthEdit(@PathVariable Long strengthId, @RequestBody SWOTRequestDto requestDto) {
        return swotService.StrengthEdit(strengthId, requestDto);
    }

    @PutMapping("/swot/weakness/{weaknessId}")
    public String weaknessEdit(@PathVariable Long weaknessId, @RequestBody SWOTRequestDto requestDto) {
        return swotService.WeaknessEdit(weaknessId, requestDto);
    }
    @PutMapping("/swot/opportunity/{opportunityId}")
    public String opportunityEdit(@PathVariable Long opportunityId, @RequestBody SWOTRequestDto requestDto) {
        return swotService.OpportunityEdit(opportunityId, requestDto);
    }
    @PutMapping("/swot/threat/{threatId}")
    public String threatEdit(@PathVariable Long threatId, @RequestBody SWOTRequestDto requestDto) {
        return swotService.ThreatEdit(threatId, requestDto);
    }
}
