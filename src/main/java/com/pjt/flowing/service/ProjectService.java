package com.pjt.flowing.service;


import com.pjt.flowing.dto.AcceptRequestDto;
import com.pjt.flowing.dto.AuthorizationDto;
import com.pjt.flowing.dto.ProjectResponseDto;
import com.pjt.flowing.dto.ProjectEditRequestDto;
import com.pjt.flowing.model.Bookmark;
import com.pjt.flowing.model.Member;
import com.pjt.flowing.model.Project;
import com.pjt.flowing.model.ProjectMember;
import com.pjt.flowing.repository.BookmarkRepository;
import com.pjt.flowing.repository.MemberRepository;
import com.pjt.flowing.repository.ProjectMemberRepository;
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
    private final ProjectMemberRepository projectMemberRepository;
    private final MemberRepository memberRepository;

    public List<ProjectResponseDto> getAll(Long userId){
        List<Project> myCreateProjects = projectRepository.findAllByMember_IdOrderByModifiedAtDesc(userId); // 자기가 만든 프로젝트 리스트
        List<ProjectResponseDto> CreateDto = myCreateProjects.stream()
                .map(ProjectResponseDto::from)
                .collect(Collectors.toList());

        List<ProjectMember> myIncludedProjects = projectMemberRepository.findAllByMember_Id(userId); // 자기가 포함된 프로젝트 리스트
        List<ProjectResponseDto> includedDto = myIncludedProjects.stream()
                .map(ProjectResponseDto::includedProject)
                .collect(Collectors.toList());

        List<ProjectResponseDto> dto = new ArrayList<>();//만든거랑 멤버로 포함된거랑 더해서 수정날짜로 정렬.
        dto.addAll(CreateDto);
        dto.addAll(includedDto);
        dto.stream().sorted(Comparator.comparing(ProjectResponseDto::getModifiedAt));
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

    @Transactional
    public String editproject(Long projectId, ProjectEditRequestDto dto){
        JSONObject obj = new JSONObject();
        Optional<Project> project = projectRepository.findById(projectId);
        if(Objects.equals(dto.getUserId(), project.get().getMember().getId())){
            project.get().update(dto);
            obj.put("msg","수정 완료");
        }
        else{
            obj.put("msg","프로젝트 장이 아닙니다");
        }
        return obj.toString();
    }

    public String detail(Long projectId){
        JSONObject obj = new JSONObject();
        Optional<Project> project = projectRepository.findById(projectId);

        //나중에 멤버리스트 추가되면  멤버 리스트일 경우만 불러올 수 있게 수정해야함.
        //어차피 멤버만 볼 수 있으니까 일단은 그냥 보여줬음.

        ProjectResponseDto dto = ProjectResponseDto.builder()
                .projectId(project.get().getId())
                .projectName(project.get().getProjectName())
                .modifiedAt(project.get().getModifiedAt())
                .thumbnailNum(project.get().getThumbNailNum())
                .build();
        obj.put("msg","불러오기");
        JSONObject DTO = new JSONObject(dto);
        obj.put("info",DTO);
        return obj.toString();
    }

    public List<ProjectResponseDto> getAllBookmarked(Long userId){
        List<Bookmark> bookmarked = bookmarkRepository.findAllByMember_IdOrderByModifiedAtDesc(userId); //userId가 누른 북마크

        List <ProjectResponseDto> dto = bookmarked.stream()
                .map(ProjectResponseDto::from2)
                .collect(Collectors.toList());
        return dto;
    }

    public List<ProjectResponseDto> getAllCreate(Long userId){
        List<Project> myCreateProjects = projectRepository.findAllByMember_IdOrderByModifiedAtDesc(userId); // 자기가 만든 프로젝트 리스트
        List<ProjectResponseDto> createDto = myCreateProjects.stream()
                .map(ProjectResponseDto::from)
                .collect(Collectors.toList());
        return createDto;
    }

    @Transactional
    public String accept(AcceptRequestDto acceptRequestDto){
        Long projectId = acceptRequestDto.getProjectId();
        Long userId = acceptRequestDto.getUserId();

        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new IllegalArgumentException("몰라")
        );
        Member member = memberRepository.findById(userId).orElseThrow(
                ()-> new IllegalArgumentException("몰라")
        );      //리팩토링 할 부분

        ProjectMember projectMember = new ProjectMember(project,member);
        projectMemberRepository.save(projectMember);
        return "수락 완료";
    }
}
