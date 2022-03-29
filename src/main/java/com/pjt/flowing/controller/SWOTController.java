package com.pjt.flowing.controller;

import com.pjt.flowing.service.SWOTService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SWOTController {

    private final SWOTService swotService;

    @PostMapping("/swot/{projectId}")
    public String CreateSWOT(@PathVariable Long projectId) {
        return swotService.swotCreate(projectId);
    }
}
