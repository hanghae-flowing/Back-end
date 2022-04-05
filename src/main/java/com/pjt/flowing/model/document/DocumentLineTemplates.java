package com.pjt.flowing.model.document;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class DocumentLineTemplates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
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
}
