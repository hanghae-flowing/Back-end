package com.pjt.flowing.controller;

import com.pjt.flowing.dto.ProjectResponseDto;
import com.pjt.flowing.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequiredArgsConstructor
@RestController
public class ProjectController {
    private final ProjectService projectService;
    @GetMapping("/api/post")
    public List<ProjectResponseDto> getProject(){
        List<ProjectResponseDto> response = projectService.getAll();
        return response;
    }

}
