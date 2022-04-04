package com.pjt.flowing.model;

import com.pjt.flowing.dto.request.NodeEditRequestDto;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Node {

    //x좌표, y좌표, width, height, text, radius, nodetableId 맵핑
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="node_id")
    private Long id;

    @Column(nullable = false)
    private int xval;

    @Column(nullable = false)
    private int yval;

    @Column(nullable = false)
    private String width;

    @Column(nullable = false)
    private String height;

    @Column
    private String text;

    @Column(nullable = false)
    private String radius;

    @Column(nullable = false)
    private int isChecked;

    @ManyToOne
    @JoinColumn(name="nodeTable_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private NodeTable nodeTable;

    @Builder
    public Node(int xval,int yval,String width,
                String height,String text,String radius,int isChecked,NodeTable nodeTable){
        this.xval=xval;
        this.yval=yval;
        this.width=width;
        this.height = height;
        this.text=text;
        this.radius=radius;
        this.isChecked=isChecked;
        this.nodeTable=nodeTable;

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
