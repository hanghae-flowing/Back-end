package com.pjt.flowing.service;

import com.pjt.flowing.dto.request.DocumentCreateRequestDto;
import com.pjt.flowing.dto.request.DocumentLineEditDto;
import com.pjt.flowing.dto.request.DocumentLineRequestDto;
import com.pjt.flowing.dto.response.DocumentLineResponseDto;
import com.pjt.flowing.model.Document;
import com.pjt.flowing.model.DocumentLine;
import com.pjt.flowing.model.Project;
import com.pjt.flowing.repository.DocumentLineRepository;
import com.pjt.flowing.repository.DocumentRepository;
import com.pjt.flowing.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final DocumentLineRepository documentLineRepository;
    private final ProjectRepository projectRepository;

    @Transactional  //기획서 생성
    public String documentCreate(DocumentCreateRequestDto dto){
        JSONObject obj = new JSONObject();
        Project project = projectRepository.findById(dto.getProjectId()).orElseThrow(
                ()-> new IllegalArgumentException("project Id error")
        );
        Document document = new Document(project);
        documentRepository.save(document);
        obj.put("documentId",document.getId());
        return obj.toString();
    }

    @Transactional  //기획서 라인 생성 첫 요청시
    public String lineCreate(DocumentLineRequestDto dto){
        Document document = documentRepository.findById(dto.getDocumentId()).orElseThrow(
                ()-> new IllegalArgumentException("document Id error")
        );

        DocumentLine documentLine = DocumentLine.builder()
                .document(document)
                .color(dto.getColor())
                .text(dto.getText())
                .fontSize(dto.getFontsize())
                .weight(dto.getWeight())
                .indexNum(dto.getIndexNum())
                .build();

        documentLineRepository.save(documentLine);
        JSONObject obj = new JSONObject();
        obj.put("lineId",documentLine.getId());
        return obj.toString();
    }

    @Transactional  //기획서 라인 수정하기
    public String lineEdit(Long documentLineId,DocumentLineEditDto dto){
        DocumentLine documentLine = documentLineRepository.findById(documentLineId).orElseThrow(
                ()->new IllegalArgumentException("not exist Line Id")
        );
        documentLine.update(dto);
        JSONObject obj = new JSONObject();
        return obj.toString();
    }

    public String showAll(Long documentId){ //기획서 라인 전부 불러오기
        List<DocumentLine> documentLineList = documentLineRepository.findAllByDocument_IdOrderByIndexNum(documentId);
        List<DocumentLineResponseDto> documentLineResponseDtoList = new ArrayList<>();
        for(DocumentLine documentLine: documentLineList){
            DocumentLineResponseDto documentLineResponseDto = DocumentLineResponseDto.builder()
                    .color(documentLine.getColor())
                    .fontSize(documentLine.getFontSize())
                    .text(documentLine.getText())
                    .weight(documentLine.getWeight())
                    .indexNum(documentLine.getIndexNum())
                    .build();
            documentLineResponseDtoList.add(documentLineResponseDto);
        }
        JSONArray jsonArray = new JSONArray(documentLineResponseDtoList);
        return jsonArray.toString();
    }
}
