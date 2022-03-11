package com.pjt.flowing.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Project extends Timestamped{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="project_id")
    private Long id;

    @Column(nullable = false)
    private String projectName;

    @Column(nullable = false)
    private Long objectId;

    @ManyToOne
    @JoinColumn(name="kakao_id")
    private Member member;

    @Column(nullable = false)
    private int thumbNailNum;
}
