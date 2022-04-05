package com.pjt.flowing.controller;


import com.pjt.flowing.dto.request.document.DocumentLineEditRequestDto;
import com.pjt.flowing.dto.request.document.DocumentLineRequestDto;
import com.pjt.flowing.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class DocumentController {
    private final DocumentService documentService;

    @GetMapping("/document/{projectId}")    // 프로젝트아이디에있는 다큐먼트 전부 찾기 (기획서열기)
    public String documentShowAll(@PathVariable Long projectId){
        return documentService.showAll(projectId);
    }

    @PostMapping("/documentLines")  // 기획서 줄 추가하기
    public String lineCreate(@RequestBody DocumentLineRequestDto dto){
        return documentService.lineCreate(dto);
    }

    @PutMapping("/documentLines/{lineId}")  //기획서 줄 수정하기
    public String lineEdit(@PathVariable Long lineId, @RequestBody DocumentLineEditRequestDto dto){
        return documentService.lineEdit(lineId,dto);
    }

    @GetMapping("/documentLines/{documentId}")  //기획서 라인 모두 불러오기
    public String showAll(@PathVariable Long documentId){
        return documentService.showAllLine(documentId);
    }

    @DeleteMapping("/documentLines/{lineId}")   //기획서 줄 삭제하기
    public String lineDelete(@PathVariable Long lineId){
        return documentService.lineDelete(lineId);
    }

}



