package com.pjt.flowing.service;


import com.pjt.flowing.dto.ProjectResponseDto;
import com.pjt.flowing.model.Bookmark;
import com.pjt.flowing.model.Project;
import com.pjt.flowing.repository.BookmarkRepository;
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
    private final BookmarkRepository bookmarkRepository;

    public List<ProjectResponseDto> getAll(Long userId){
        List<Project> all = projectRepository.findAllByMember_IdOrderByModifiedAtDesc(userId);
        List<ProjectResponseDto> dto = all.stream()
                .map(ProjectResponseDto::from)
                .collect(Collectors.toList());
        return dto;
    }

    public List<ProjectResponseDto> get4(Long userId){
        //List<Project> all = projectRepository.findAllByMember_IdOrderByModifiedAtDesc(userId);
        List<Project> bookmarked = bookmarkRepository.findAllByMember_IdOrderByModifiedAtDesc(userId);
        for(int i = 0 ;i<4;i++){
            System.out.println(bookmarked.get(i));
        }
        List<ProjectResponseDto> dto = bookmarked.stream()
                .map(ProjectResponseDto::from)
                .collect(Collectors.toList());
        return dto;
    }
}
