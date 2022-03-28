package com.pjt.flowing.controller;


import com.pjt.flowing.dto.request.DocumentLineEditRequestDto;
import com.pjt.flowing.dto.request.DocumentLineRequestDto;
import com.pjt.flowing.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class DocumentController {
    private final DocumentService documentService;

    @PostMapping("/document/{projectId}")   //기획서 생성
    public String documentCreate(@PathVariable Long projectId){
        return documentService.documentCreate(projectId);
    }

    @PostMapping("/documentLines")  //기획서 줄 추가하기
    public String lineCreate(@RequestBody DocumentLineRequestDto dto){
        return documentService.lineCreate(dto);
    }

    @PutMapping("/documentLines/{lineId}")  //기획서 줄 수정하기
    public String lineEdit(@PathVariable Long lineId, @RequestBody DocumentLineEditRequestDto dto){
        return documentService.lineEdit(lineId,dto);
    }

    @GetMapping("/documentLines/{documentId}")  //기획서 줄 모두 불러오기
    public String showAll(@PathVariable Long documentId){
        return documentService.showAll(documentId);
    }

    @DeleteMapping("/documentLines/{lineId}")   //기획서 줄 삭제하기
    public String lineDelete(@PathVariable Long lineId){
        return documentService.lineDelete(lineId);
    }

}



