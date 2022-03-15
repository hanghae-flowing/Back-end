package com.pjt.flowing.service;


import com.pjt.flowing.dto.ProjectResponseDto;
import com.pjt.flowing.model.Bookmark;
import com.pjt.flowing.model.Project;
import com.pjt.flowing.repository.BookmarkRepository;
import com.pjt.flowing.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final BookmarkRepository bookmarkRepository;

    public List<ProjectResponseDto> getAll(Long userId){
        List<Project> all = projectRepository.findAllByMember_IdOrderByModifiedAtDesc(userId);
        List<ProjectResponseDto> dto = all.stream()
                .map(ProjectResponseDto::from)
                .collect(Collectors.toList());
        return dto;
    }

    public List<ProjectResponseDto> get4(Long userId){
        List<Project> all = projectRepository.findFirst4ByMember_IdOrderByModifiedAtDesc(userId);
        List<Bookmark> bookmarked = bookmarkRepository.findAllByMember_IdOrderByModifiedAtDesc(userId); //userId가 누른 북마크

        List <ProjectResponseDto> re = bookmarked.stream()
                .map(ProjectResponseDto::from2)
                .collect(Collectors.toList());


        List<ProjectResponseDto> dto = all.stream()
                .map(ProjectResponseDto::from)
                .collect(Collectors.toList());

        List<ProjectResponseDto> joined = new ArrayList<>();
        joined.addAll(re);
        joined.addAll(dto);

        List<ProjectResponseDto> response = joined.stream()
                .limit(4)
                .distinct()
                .collect(Collectors.toList());


        return response;
    }
}
