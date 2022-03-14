package com.pjt.flowing.service;


import com.pjt.flowing.dto.ProjectResponseDto;
import com.pjt.flowing.model.Project;
import com.pjt.flowing.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ProjectService {
    private final ProjectRepository projectRepository;

    public List<ProjectResponseDto> getAll(Long userId){
        List<Project> all = projectRepository.findAllByMemberId(userId);
        List<ProjectResponseDto> dto = all.stream()
                .map(ProjectResponseDto::from)
                .collect(Collectors.toList());
        return dto;
    }
}
