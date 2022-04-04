package com.pjt.flowing.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pjt.flowing.dto.request.FolderAddProjectRequestDto;
import com.pjt.flowing.dto.request.FolderCreateRequestDto;
import com.pjt.flowing.dto.request.FolderDeleteProjectRequestDto;
import com.pjt.flowing.dto.request.FolderRequestDto;
import com.pjt.flowing.dto.response.FolderTableResponseDto;
import com.pjt.flowing.dto.response.GapNodeResponseDto;
import com.pjt.flowing.dto.response.ProjectResponseDto;
import com.pjt.flowing.model.*;
import com.pjt.flowing.repository.FolderRepository;
import com.pjt.flowing.repository.FolderTableRepository;
import com.pjt.flowing.repository.MemberRepository;
import com.pjt.flowing.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class FolderService {

    private final FolderTableRepository folderTableRepository;
    private final FolderRepository folderRepository;
    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;
    // 폴더 생성
    @Transactional
    public String createFolder(FolderCreateRequestDto requestDto){

        Member member = memberRepository.findById(requestDto.getUserId()).orElseThrow(()->new IllegalArgumentException("멤버오류"));
        FolderTable folderTable = new FolderTable(requestDto.getFolderName(),member);
        folderTableRepository.save(folderTable);

        JSONObject obj = new JSONObject();
        obj.put("msg","폴더 생성 완료");
        return obj.toString();
    }

    // 폴더에 프로젝트 추가.
    @Transactional
    public String addProjectFolder(FolderAddProjectRequestDto requestDto){
        FolderTable folderTable = folderTableRepository.findById(requestDto.getFolderTableId()).orElseThrow(
                ()->new IllegalArgumentException("폴더테이블오류")
        );
        Folder folder = new Folder(folderTable,requestDto.getProjectId());

        folderRepository.save(folder);
        JSONObject obj = new JSONObject();
        obj.put("msg","폴더에 프로젝트 추가 완료");
        return obj.toString();
    }

    // 폴더정보 조회하기
    public List<FolderTableResponseDto> getFolderAll(Long userId){
        List<FolderTable> folderTableList =  folderTableRepository.findAllByMember_Id(userId);
        List<FolderTableResponseDto> folderDto = folderTableList.stream()
                .filter(x -> !x.isTrash())
                .map(FolderTableResponseDto::myFolder)
                .sorted(Comparator.comparing(FolderTableResponseDto::getModifiedAt).reversed())
                .collect(Collectors.toList());
        return folderDto;
    }

    // 폴더 휴지통에 보내기
    @Transactional
    public String trashFolder(FolderRequestDto requestDto){
        FolderTable folderTable = folderTableRepository.findById(requestDto.getFolderTableId()).orElseThrow(
                ()->new IllegalArgumentException("폴더테이블")
        );
        folderTable.setTrash(true);
        JSONObject obj = new JSONObject();
        obj.put("msg","폴더 휴지통 보내기 완료");
        return obj.toString();
    }

    //폴더 삭제하기.
    @Transactional
    public String deleteFolder(FolderRequestDto requestDto){
        folderTableRepository.deleteById(requestDto.getFolderTableId());
        JSONObject obj = new JSONObject();
        obj.put("msg","폴더 삭제 완료");
        return obj.toString();
    }
    // 폴더에들어있는 프로젝트 삭제
    @Transactional
    public String deleteFolderProject(FolderDeleteProjectRequestDto requestDto){
        folderRepository.deleteByFolderTable_IdAndAndProjectId(requestDto.getFolderTableId(),requestDto.getProjectId());
        JSONObject obj = new JSONObject();
        obj.put("msg","폴더에 프로젝트 삭제 완료");
        return obj.toString();
    }

    // 폴더에 들어있는 프로젝트 조회
    public List<ProjectResponseDto> getProjectAll(Long folderTableId) {
        List<Folder>projectList = folderRepository.findAllByFolderTable_Id(folderTableId);
        List<Project> projects = new ArrayList<>();
//        for(Folder folder: projectList){
//            Project project = projectRepository.findById(folder.getProjectId()).orElseThrow(
//                    ()->new IllegalArgumentException("프로젝트폴더")
//            );
//            projects.add(project);
//        }
        // for문을 stream으로 변경
        projectList.stream()
                .map(x->projects.add(projectRepository.findById(x.getProjectId()).orElseThrow(
                                        ()->new IllegalArgumentException("프로젝트폴더")
                                )
                ));


        List<ProjectResponseDto> dto = projects.stream()
                .map(ProjectResponseDto::from)
                .sorted(Comparator.comparing(ProjectResponseDto::getModifiedAt).reversed())
                .collect(Collectors.toList());
        return dto;
    }


}
