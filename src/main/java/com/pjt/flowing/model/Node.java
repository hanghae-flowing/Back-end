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
    private int xVal;

    @Column(nullable = false)
    private int yVal;

    @Column(nullable = false)
    private int width;

    @Column(nullable = false)
    private int height;

    @Column
    private String text;

    @Column(nullable = false)
    private int radius;

    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;

}
