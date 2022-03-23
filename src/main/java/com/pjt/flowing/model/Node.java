package com.pjt.flowing.model;

import com.pjt.flowing.dto.NodeEditRequestDto;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
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
    private String xval;

    @Column(nullable = false)
    private String yval;

    @Column(nullable = false)
    private String width;

    @Column(nullable = false)
    private String height;

    @Column
    private String text;

    @Column(nullable = false)
    private String radius;

    @Column(nullable = false)
    private int isChecked;  //chap2~3에서 키워드 보여주려면 필요함

    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;

    @Builder
    public Node(String xval,String yval,String width,
                String height,String text,String radius,int isChecked,Project project){
        this.xval=xval;
        this.yval=yval;
        this.width=width;
        this.height = height;
        this.text=text;
        this.radius=radius;
        this.isChecked=isChecked;
        this.project=project;

    }

    public void update(NodeEditRequestDto dto){
        this.xval=dto.getXval();
        this.yval=dto.getYval();
        this.radius=dto.getRadius();
        this.width=dto.getWidth();
        this.height=dto.getHeight();
        this.text=dto.getText();
    }

}
