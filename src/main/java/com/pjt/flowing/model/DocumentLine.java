package com.pjt.flowing.model;

import com.pjt.flowing.dto.request.DocumentLineEditRequestDto;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DocumentLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="line_id")
    private Long id;

    @Column(length=500)
    private String text;

    @Column(nullable = false)
    private int weight;

    @Column(nullable = false)
    private int fontSize;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private int indexNum;

    @ManyToOne
    @JoinColumn(name="document_id")
    private Document document;

    @Builder
    public DocumentLine(String text, int weight, int fontSize, String color, Document document, int indexNum) {
        this.text = text;
        this.weight = weight;
        this.fontSize = fontSize;
        this.color = color;
        this.document = document;
        this.indexNum = indexNum;
    }

    public void update(DocumentLineEditRequestDto dto){
        this.text=dto.getText();
        this.weight=dto.getWeight();
        this.fontSize=dto.getFontsize();
        this.color=dto.getColor();
        this.indexNum=dto.getIndexNum();

    }

}