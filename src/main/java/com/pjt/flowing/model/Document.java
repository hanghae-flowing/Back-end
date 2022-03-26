package com.pjt.flowing.model;

import lombok.*;
import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Document {

    // 빈껍데기에용
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

}