package com.pjt.flowing.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Node {

    //x좌표, y좌표, width, height, text, radius, projectId 맵핑
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="node_id")
    private Long id;

    @Column(nullable = false)
    private String xVal;

    @Column(nullable = false)
    private String yVal;

    @Column(nullable = false)
    private String width;

    @Column(nullable = false)
    private String height;

    @Column
    private String text;

    @Column(nullable = false)
    private String radius;

    @Column(nullable = false)
    private boolean isChecked;  //chap2~3에서 키워드 보여주려면 필요함

    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;


}
