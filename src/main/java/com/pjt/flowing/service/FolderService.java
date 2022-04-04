package com.pjt.flowing.service;

import com.pjt.flowing.dto.request.FolderAddProjectRequestDto;
import com.pjt.flowing.dto.request.FolderCreateRequestDto;
import com.pjt.flowing.model.Folder;
import com.pjt.flowing.model.FolderTable;
import com.pjt.flowing.model.Member;
import com.pjt.flowing.repository.FolderRepository;
import com.pjt.flowing.repository.FolderTableRepository;
import com.pjt.flowing.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class FolderService {

    private final FolderTableRepository folderTableRepository;
    private final FolderRepository folderRepository;
    private final MemberRepository memberRepository;
    // 폴더 생성
    public String createFolder(FolderCreateRequestDto requestDto){

        Member member = memberRepository.findById(requestDto.getUserId()).orElseThrow(()->new IllegalArgumentException("멤버오류"));
        FolderTable folderTable = new FolderTable(requestDto.getFolderName(),member);
        folderTableRepository.save(folderTable);

        JSONObject obj = new JSONObject();
        obj.put("msg","폴더 생성 완료");
        return obj.toString();
    }

    // 폴더에 프로젝트 추가.
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
}
