package com.pjt.flowing.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.pjt.flowing.dto.request.AcceptRequestDto;
import com.pjt.flowing.dto.AuthorizationDto;
import com.pjt.flowing.dto.request.ProjectCreateRequestDto;
import com.pjt.flowing.dto.response.*;
import com.pjt.flowing.dto.request.ProjectEditRequestDto;
import com.pjt.flowing.model.*;
import com.pjt.flowing.model.swot.SWOT;
import com.pjt.flowing.repository.*;
import com.pjt.flowing.repository.swot.SWOTRepository;
import com.pjt.flowing.security.Authorization;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

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
    private final DocumentRepository documentRepository;
    private final NodeTableRepository nodeTableRepository;
    private final GapTableRepository gapTableRepository;
    private final SWOTRepository swotRepository;
    private final Authorization authorization;
    private final NodeService nodeService;
    private final DocumentService documentService;
    private final GapNodeService gapNodeService;

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
    public List<ProjectResponseDto> getAll(Long userId){
//        List<Project> myCreateProjects = projectRepository.findAllByMember_IdOrderByModifiedAtDesc(userId); // 자기가 만든 프로젝트 리스트
//        List<ProjectResponseDto> CreateDto = myCreateProjects.stream()
//                .map(ProjectResponseDto::from)
//                .collect(Collectors.toList());

        List<ProjectMember> myIncludedProjects = projectMemberRepository.findAllByMember_Id(userId); // 자기가 포함된 프로젝트 리스트
        List<ProjectResponseDto> includedDto = myIncludedProjects.stream()
                .map(ProjectResponseDto::includedProject)
                .sorted(Comparator.comparing(ProjectResponseDto::getModifiedAt))
                .collect(Collectors.toList());


        return includedDto;
    }


//    public List<ProjectResponseDto> get4(Long userId){
//        List<Project> all = projectRepository.findFirst4ByMember_IdOrderByModifiedAtDesc(userId);
//        List<Bookmark> bookmarked = bookmarkRepository.findAllByMember_IdOrderByModifiedAtDesc(userId); //userId가 누른 북마크
//
//        List <ProjectResponseDto> re = bookmarked.stream()
//                .map(ProjectResponseDto::from2)
//                .collect(Collectors.toList());
//
//
//        List<ProjectResponseDto> dto = all.stream()
//                .map(ProjectResponseDto::from)
//                .collect(Collectors.toList());
//
//        List<ProjectResponseDto> joined = new ArrayList<>();
//        joined.addAll(re);
//        joined.addAll(dto);
//
//        List<ProjectResponseDto> response =joined.stream()
//                .filter(distinctByKey(ProjectResponseDto::getProjectId))
//                .limit(4)
//                .collect(Collectors.toList());
//
//        return response;
//    }

    @Transactional
    public String deleteProject(Long projectId, AuthorizationDto dto){
        JSONObject obj = new JSONObject();
        Project project = projectRepository.findById(projectId).orElseThrow(
                ()->new IllegalArgumentException("no project Id error")
        );
        if(dto.getUserId() == project.getMember().getId()){
            projectRepository.deleteById(projectId);
            obj.put("msg","삭제완료");
        }
        else{
            obj.put("msg","프로젝트 장이 아닙니다");
        }
        return obj.toString();
    }

    @Transactional
    public String editProject(Long projectId, ProjectEditRequestDto dto){
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
        Project project = projectRepository.findById(projectId).orElseThrow(
                ()->new IllegalArgumentException("projectId error")
        );
        //한개만있음
        Document document = documentRepository.findByProject_Id(projectId);
        GapTable gapTable = gapTableRepository.findByProject_Id(projectId);
        NodeTable nodeTable = nodeTableRepository.findByProject_Id(projectId);

        ProjectResponseDto dto = ProjectResponseDto.builder()
                .projectId(project.getId())
                .projectName(project.getProjectName())
                .modifiedAt(project.getModifiedAt())
                .thumbnailNum(project.getThumbNailNum())
                .build();
        obj.put("msg","불러오기");
        JSONObject DTO = new JSONObject(dto);
        obj.put("projectInfo",DTO);
        obj.put("documentId",document.getId());
        obj.put("gapTableId",gapTable.getId());
        obj.put("nodeTable",nodeTable.getId());
        return obj.toString();
    }

    public List<ProjectResponseDto> getAllBookmarked(Long userId){
        List<Bookmark> bookmarked = bookmarkRepository.findAllByMember_IdOrderByModifiedAtDesc(userId); //userId가 누른 북마크

        return bookmarked.stream()
                .map(ProjectResponseDto::from2)
                .collect(Collectors.toList());
    }

    public List<ProjectResponseDto> getAllCreate(Long userId){
        List<Project> myCreateProjects = projectRepository.findAllByMember_IdOrderByModifiedAtDesc(userId); // 자기가 만든 프로젝트 리스트
        return myCreateProjects.stream()
                .map(ProjectResponseDto::from)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public String accept(AcceptRequestDto acceptRequestDto){
        Long projectId = acceptRequestDto.getProjectId();
        Long userId = acceptRequestDto.getUserId();

        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new IllegalArgumentException("accept (project) error")
        );
        Member member = memberRepository.findById(userId).orElseThrow(
                ()-> new IllegalArgumentException("accept (member) error")
        );

        ProjectMember projectMember = new ProjectMember(project,member);
        projectMemberRepository.save(projectMember);
        JSONObject obj = new JSONObject();
        obj.put("msg","수락 완료");
        return obj.toString();
    }

    public String showTemplates(Long projectid){
        List<Document> documentList = documentRepository.findAllByProject_Id(projectid);
        List<NodeTable> nodeTableList = nodeTableRepository.findAllByProject_Id(projectid);
        List<GapTable> gapTableList = gapTableRepository.findAllByProject_Id(projectid);
        List<SWOT> swotList = swotRepository.findAllByProject_Id(projectid);

        List<DocumentIdResponseDto> documentIdResponseDtoList = new ArrayList<>();
        for(Document document: documentList){
            DocumentIdResponseDto documentIdResponseDto = new DocumentIdResponseDto(document.getId());
            documentIdResponseDtoList.add(documentIdResponseDto);
        }
        List<NodeTableIdResponseDto> nodeTableIdResponseDtoList = new ArrayList<>();
        for(NodeTable nodeTable: nodeTableList){
            NodeTableIdResponseDto nodeTableIdResponseDto = new NodeTableIdResponseDto(nodeTable.getId());
            nodeTableIdResponseDtoList.add(nodeTableIdResponseDto);
        }
        List<GapTableIdResponseDto> gapTableIdResponseDtoList = new ArrayList<>();
        for(GapTable gapTable: gapTableList){
            GapTableIdResponseDto gapTableIdResponseDto = new GapTableIdResponseDto(gapTable.getId());
            gapTableIdResponseDtoList.add(gapTableIdResponseDto);
        }
        List<SwotIdResponseDto> swotIdResponseDtoList = new ArrayList<>();
        for(SWOT swot: swotList){
            SwotIdResponseDto swotIdResponseDto = new SwotIdResponseDto(swot.getId());
            swotIdResponseDtoList.add(swotIdResponseDto);
        }

        JSONObject obj = new JSONObject();
        obj.put("msg","템플릿 리스트 불러오기");
        obj.put("documentIdList",documentIdResponseDtoList);
        obj.put("nodeTableIdList",nodeTableIdResponseDtoList);
        obj.put("gapTableIdList",gapTableIdResponseDtoList);
        obj.put("swotIdList",swotIdResponseDtoList);

        return obj.toString();
    }

    //프로젝트 생성하기
    public String createProject(ProjectCreateRequestDto projectCreateRequestDto) throws JsonProcessingException {
        AuthorizationDto authorizationDto = new AuthorizationDto(projectCreateRequestDto.getAccessToken(),projectCreateRequestDto.getKakaoId(),projectCreateRequestDto.getUserId());
        JSONObject obj = new JSONObject();
        if (authorization.getKakaoId(authorizationDto) == 0){
            obj.put("msg","false");
            return obj.toString();
        }
        Member member = memberRepository.findById(projectCreateRequestDto.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("no Id")
        );
        Project project = new Project(
                projectCreateRequestDto.getProjectName(),
                projectCreateRequestDto.getObjectId(),
                member,
                projectCreateRequestDto.getThumbNailNum()
        );
        projectRepository.save(project);

        obj.put("msg","true");
        obj.put("projectId",project.getId());

        ProjectMember projectMember = new ProjectMember(project, member);
        projectMemberRepository.save(projectMember);

        //여기부터 임시로 만든곳임 나중에 지우면됨 levelDown
        obj.put("nodeTableId",nodeService.nodeTableCreate(project.getId()));
        obj.put("gapTableId",gapNodeService.gapTableCreate(project.getId()));
        obj.put("documentInfo",documentService.documentCreate(project.getId()));
        return obj.toString();
    }

    //북마크 생성하기
    public String checkBookmark(Long projectId ,AuthorizationDto authorizationDto){
        boolean check = bookmarkRepository.existsByMember_IdAndProject_Id(authorizationDto.getUserId(), projectId);
        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new IllegalArgumentException("no Project")
        );
        Member member = memberRepository.findById(authorizationDto.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("no Id")
        );
        JSONObject obj = new JSONObject();
        if (!check) {
            Bookmark bookmark = new Bookmark(project, member);
            bookmarkRepository.save(bookmark);
            obj.put("msg","check");
        }
        else {
            bookmarkRepository.deleteByMember_IdAndProject_Id(authorizationDto.getUserId(), projectId);
            obj.put("msg","cancel");
        }
        return obj.toString();
    }
}
