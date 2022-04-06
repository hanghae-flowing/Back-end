package com.pjt.flowing.service;

import com.pjt.flowing.dto.request.folder.FolderAddProjectRequestDto;
import com.pjt.flowing.dto.request.folder.FolderCreateRequestDto;
import com.pjt.flowing.dto.request.folder.FolderDeleteProjectRequestDto;
import com.pjt.flowing.dto.request.folder.FolderRequestDto;
import com.pjt.flowing.dto.response.folder.FolderTableResponseDto;
import com.pjt.flowing.dto.response.project.ProjectResponseDto;
import com.pjt.flowing.model.*;
import com.pjt.flowing.model.folder.Folder;
import com.pjt.flowing.model.folder.FolderTable;
import com.pjt.flowing.model.project.Project;
import com.pjt.flowing.repository.folder.FolderRepository;
import com.pjt.flowing.repository.folder.FolderTableRepository;
import com.pjt.flowing.repository.MemberRepository;
import com.pjt.flowing.repository.project.ProjectRepository;
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
    public String createFolder(FolderCreateRequestDto requestDto) {

        Member member = memberRepository.findById(requestDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("멤버오류"));
        FolderTable folderTable = new FolderTable(requestDto.getFolderName(), member);
        folderTableRepository.save(folderTable);

        JSONObject obj = new JSONObject();
        obj.put("msg", "폴더 생성 완료");
        return obj.toString();
    }

    // 폴더에 프로젝트 추가.
    @Transactional
    public String addProjectFolder(FolderAddProjectRequestDto requestDto) {
        JSONObject obj = new JSONObject();
        FolderTable folderTable = folderTableRepository.findById(requestDto.getFolderTableId()).orElseThrow(
                () -> new IllegalArgumentException("폴더테이블오류")
        );
        if (projectRepository.existsById(requestDto.getProjectId())) {   //프로젝트가 존재한다면
            if (!folderRepository.existsByFolderTable_idAndProjectId(folderTable.getId(), requestDto.getProjectId())) { // 폴더에 프로젝트가 이미 존재하지않는다면

                Folder folder = new Folder(folderTable, requestDto.getProjectId());

                folderRepository.save(folder);
                obj.put("msg", "폴더에 프로젝트 추가 완료");

            } else {
                obj.put("msg", "이미 폴더에 프로젝트가 있음");
            }
        } else {
            obj.put("msg", "프로젝트아이디가 없음");
        }

        return obj.toString();
    }

    // 폴더정보 조회하기
    public List<FolderTableResponseDto> getFolderAll(Long userId) {
        List<FolderTable> folderTableList = folderTableRepository.findAllByMember_Id(userId);
        List<FolderTableResponseDto> folderDto = folderTableList.stream()
                .filter(x -> !x.isTrash())
                .map(FolderTableResponseDto::myFolder)
                .sorted(Comparator.comparing(FolderTableResponseDto::getModifiedAt).reversed())
                .collect(Collectors.toList());
        return folderDto;
    }

    // 휴지통에 있는 폴더정보 조회하기
    public List<FolderTableResponseDto> getTrashFolderAll(Long userId) {
        List<FolderTable> folderTableList = folderTableRepository.findAllByMember_Id(userId);
        List<FolderTableResponseDto> folderDto = folderTableList.stream()
                .filter(x -> x.isTrash())
                .map(FolderTableResponseDto::myFolder)
                .sorted(Comparator.comparing(FolderTableResponseDto::getModifiedAt).reversed())
                .collect(Collectors.toList());
        return folderDto;
    }

    // 폴더 휴지통에 보내기
    @Transactional
    public String trashFolder(FolderRequestDto requestDto) {
        FolderTable folderTable = folderTableRepository.findById(requestDto.getFolderTableId()).orElseThrow(
                () -> new IllegalArgumentException("폴더테이블")
        );
        folderTable.setTrash(true);
        JSONObject obj = new JSONObject();
        obj.put("msg", "폴더 휴지통 보내기 완료");
        return obj.toString();
    }

    // 폴더 휴지통에서 복구하기
    @Transactional
    public String restoreFolder(FolderRequestDto requestDto) {
        FolderTable folderTable = folderTableRepository.findById(requestDto.getFolderTableId()).orElseThrow(
                () -> new IllegalArgumentException("폴더테이블")
        );
        folderTable.setTrash(false);
        JSONObject obj = new JSONObject();
        obj.put("msg", "폴더 복구 완료");
        return obj.toString();
    }

    //폴더 삭제하기.
    @Transactional
    public String deleteFolder(FolderRequestDto requestDto) {
        //해당하는 폴더 가서 프로젝트들 찾고 ㄹ

        folderTableRepository.deleteById(requestDto.getFolderTableId());
        JSONObject obj = new JSONObject();
        obj.put("msg", "폴더 삭제 완료");
        return obj.toString();
    }

    // 폴더에 들어있는 프로젝트 메인으로 보내기
    @Transactional
    public String deleteFolderProject(FolderDeleteProjectRequestDto requestDto) {
        folderRepository.deleteByFolderTable_IdAndAndProjectId(requestDto.getFolderTableId(), requestDto.getProjectId());
        JSONObject obj = new JSONObject();
        obj.put("msg", "폴더에서 나가");
        return obj.toString();
    }

    // 폴더에 들어있는 프로젝트 조회
    public List<ProjectResponseDto> getProjectAll(Long folderTableId) {
        List<Folder> projectList = folderRepository.findAllByFolderTable_Id(folderTableId);
        List<Project> projects = new ArrayList<>();
        for (Folder folder : projectList) {
            Project project = projectRepository.findById(folder.getProjectId()).orElseThrow(
                    () -> new IllegalArgumentException("프로젝트폴더")
            );
            projects.add(project);
        }

        List<ProjectResponseDto> dto = projects.stream()
                .map(ProjectResponseDto::from)
                .sorted(Comparator.comparing(ProjectResponseDto::getModifiedAt).reversed())
                .collect(Collectors.toList());
        return dto;
    }

    // 폴더 북마크
    @Transactional
    public String bookmarkFolder(FolderRequestDto requestDto) {
        FolderTable folderTable = folderTableRepository.findById(requestDto.getFolderTableId()).orElseThrow(
                () -> new IllegalArgumentException("폴더테이블")
        );
        JSONObject obj = new JSONObject();
        if (folderTable.isBookmark()) {//이미 북마크 되어있음
            folderTable.setBookmark(false);
            obj.put("msg", "폴더 북마크 해제");
        } else {
            folderTable.setBookmark(true);
            obj.put("msg", "폴더 북마크 적용");
        }

        return obj.toString();
    }

    //북마크된 폴더만 조회하기
    public List<FolderTableResponseDto> getFolderBookmarked(Long userId) {
        List<FolderTable> folderTableList = folderTableRepository.findAllByMember_Id(userId);
        List<FolderTableResponseDto> folderDto = folderTableList.stream()
                .filter(x -> !x.isTrash())
                .filter(x -> x.isBookmark())
                .map(FolderTableResponseDto::myFolder)
                .sorted(Comparator.comparing(FolderTableResponseDto::getModifiedAt).reversed())
                .collect(Collectors.toList());
        return folderDto;
    }


}
