package com.pjt.flowing.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pjt.flowing.dto.AuthorizationDto;
import com.pjt.flowing.dto.ProjectResponseDto;
import com.pjt.flowing.security.Authorization;
import com.pjt.flowing.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
public class ProjectController {
    private final ProjectService projectService;
    private final Authorization authorization;
    @PostMapping("api/project/readAll")
    public List<ProjectResponseDto> getProject(@RequestBody AuthorizationDto requestDto) throws JsonProcessingException {
        if(authorization.getKakaoId(requestDto)==0){
            System.out.println("인가x");
        }
        List<ProjectResponseDto> response = projectService.getAll(requestDto.getKakaoId());

        return response;
    }

}
