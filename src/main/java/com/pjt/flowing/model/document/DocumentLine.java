package com.pjt.flowing.model.document;

import com.pjt.flowing.dto.request.document.DocumentLineEditRequestDto;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @Column
    private int maxLength;

    @ManyToOne
    @JoinColumn(name="document_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Document document;

    @Column(length=500)
    private String placeHolder;

    @Builder
    public DocumentLine(String text, int weight, int fontSize, String color, Document document,
                        int indexNum, Long id, int maxLength,String placeHolder) {
        this.text = text;
        this.weight = weight;
        this.fontSize = fontSize;
        this.color = color;
        this.document = document;
        this.indexNum = indexNum;
        this.id=id;
        this.maxLength=maxLength;
        this.placeHolder=placeHolder;
    }

    public void update(DocumentLineEditRequestDto dto){
        this.text=dto.getText();
        this.weight=dto.getWeight();
        this.fontSize=dto.getFontSize();
        this.color=dto.getColor();
        this.indexNum=dto.getIndexNum();
        this.maxLength=dto.getMaxLength();
    }
}