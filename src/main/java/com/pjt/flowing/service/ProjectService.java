package com.pjt.flowing.service;


import com.pjt.flowing.dto.AuthorizationDto;
import com.pjt.flowing.dto.ProjectResponseDto;
import com.pjt.flowing.model.Bookmark;
import com.pjt.flowing.model.Project;
import com.pjt.flowing.repository.BookmarkRepository;
import com.pjt.flowing.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
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
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
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

        List<ProjectResponseDto> response =joined.stream()
                .filter(distinctByKey(ProjectResponseDto::getProjectId))
                .limit(4)
                .collect(Collectors.toList());

        return response;
    }

    @Transactional
    public String deleteproject(Long projectId, AuthorizationDto dto){
        JSONObject obj = new JSONObject();
        Optional<Project> project = projectRepository.findById(projectId);
        if(Objects.equals(dto.getUserId(), project.get().getMember().getId())){
            projectRepository.deleteById(projectId);
            obj.put("msg","삭제완료");
        }
        else{
            obj.put("msg","프로젝트 장이 아닙니다");
        }
        return obj.toString();
    }
}
