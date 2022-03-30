package com.pjt.flowing.service;

import com.pjt.flowing.dto.request.DocumentLineEditRequestDto;
import com.pjt.flowing.dto.request.DocumentLineRequestDto;
import com.pjt.flowing.dto.response.DocumentIdResponseDto;
import com.pjt.flowing.dto.response.DocumentLineResponseDto;
import com.pjt.flowing.model.Document;
import com.pjt.flowing.model.DocumentLine;
import com.pjt.flowing.model.DocumentLineTemplates;
import com.pjt.flowing.model.Project;
import com.pjt.flowing.model.repository.DocumentLineRepository;
import com.pjt.flowing.model.repository.DocumentLineTemplatesRepository;
import com.pjt.flowing.model.repository.DocumentRepository;
import com.pjt.flowing.model.repository.ProjectRepository;
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
    private final DocumentLineTemplatesRepository documentLineTemplatesRepository;

    @Transactional  //기획서 생성 시키면서 default template 값 넣어주기
    public String documentCreate(Long projectId){
        JSONObject obj = new JSONObject();
        Project project = projectRepository.findById(projectId).orElseThrow(
                ()-> new IllegalArgumentException("project Id error")
        );
        Document document = new Document(project);
        documentRepository.save(document);
        obj.put("documentId",document.getId());

        List<DocumentLineResponseDto> documentLineResponseDtoList = new ArrayList<>();
        for(long i = 1L; i<18L; i++){
            System.out.println("i값 찍어봐용"+i);
            DocumentLineTemplates templates = documentLineTemplatesRepository.findById(i).orElseThrow(
                    ()->new IllegalArgumentException("templates download error")
            );
            DocumentLine documentLine = DocumentLine.builder()
                    .indexNum(templates.getIndexNum())
                    .weight(templates.getWeight())
                    .fontSize(templates.getFontSize())
                    .text(templates.getText())
                    .color(templates.getColor())
                    .document(document)
                    .build();
            documentLineRepository.save(documentLine);
            DocumentLineResponseDto documentLineResponseDto = DocumentLineResponseDto.builder()
                    .color(documentLine.getColor())
                    .fontSize(documentLine.getFontSize())
                    .text(documentLine.getText())
                    .weight(documentLine.getWeight())
                    .indexNum(documentLine.getIndexNum())
                    .lineId(documentLine.getId())
                    .build();
            documentLineResponseDtoList.add(documentLineResponseDto);
        }
        JSONArray array = new JSONArray(documentLineResponseDtoList);
        obj.append("templatesInfo",array);
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
                .fontSize(dto.getFontSize())
                .weight(dto.getWeight())
                .indexNum(dto.getIndexNum())
                .build();

        documentLineRepository.save(documentLine);
        JSONObject obj = new JSONObject();
        obj.put("lineId",documentLine.getId());
        return obj.toString();
    }

    @Transactional  //기획서 라인 수정하기
    public String lineEdit(Long lineId, DocumentLineEditRequestDto dto){
        DocumentLine documentLine = documentLineRepository.findById(lineId).orElseThrow(
                ()->new IllegalArgumentException("not exist Line Id")
        );
        documentLine.update(dto);
        DocumentLineResponseDto documentLineResponseDto = DocumentLineResponseDto.builder()
                .lineId(documentLine.getId())
                .indexNum(documentLine.getIndexNum())
                .weight(documentLine.getWeight())
                .text(documentLine.getText())
                .fontSize(documentLine.getFontSize())
                .color(documentLine.getColor())
                .build();
        JSONObject obj = new JSONObject(documentLineResponseDto);
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
                    .lineId(documentLine.getId())
                    .build();
            documentLineResponseDtoList.add(documentLineResponseDto);
        }
        JSONArray jsonArray = new JSONArray(documentLineResponseDtoList);
        return jsonArray.toString();
    }

    @Transactional
    public String lineDelete(Long lineId){
        documentLineRepository.deleteById(lineId);
        JSONObject obj = new JSONObject();
        obj.put("msg","기획서 라인 삭제");
        return obj.toString();
    }

    public String Showall(Long projectId){
        List<Document> documentList = documentRepository.findAllByProject_Id(projectId);
        List<DocumentIdResponseDto> documentIdResponseDtoList = new ArrayList<>();
        JSONObject obj = new JSONObject();
        for(Document document: documentList){
            DocumentIdResponseDto documentIdResponseDto = new DocumentIdResponseDto(document.getId());
            documentIdResponseDtoList.add(documentIdResponseDto);

        }
        obj.put("msg","기획서 리스트 불러오기");
        obj.put("documentIdList",documentIdResponseDtoList);
        return obj.toString();
    }
}
