package com.pjt.flowing.controller;


import com.pjt.flowing.dto.request.DocumentCreateRequestDto;
import com.pjt.flowing.dto.request.DocumentLineEditDto;
import com.pjt.flowing.dto.request.DocumentLineRequestDto;
import com.pjt.flowing.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class DocumentController {
    private final DocumentService documentService;

    @PostMapping("/document")   //기획서 생성
    public String documentCreate(@RequestBody DocumentCreateRequestDto dto){
        return documentService.documentCreate(dto);
    }

    @PostMapping("/documentLines")  //기획서 줄 추가하기
    public String lineCreate(@RequestBody DocumentLineRequestDto dto){
        return documentService.lineCreate(dto);
    }

    @PutMapping("/documentLines/{documentLineId}")  //기획서 줄 수정하기
    public String lineEdit(@PathVariable Long documentLineId, @RequestBody DocumentLineEditDto dto){
        return documentService.lineEdit(documentLineId,dto);
    }

    @GetMapping("/documentLines/{documentId}")  //기획서 줄 모두 불러오기
    public String showAll(@PathVariable Long documentId){
        return documentService.showAll(documentId);
    }


}



