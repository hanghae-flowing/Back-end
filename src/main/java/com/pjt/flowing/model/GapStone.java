package com.pjt.flowing.model;

import com.pjt.flowing.dto.request.GapStoneRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class GapStone {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="gapStone_id")
    private Long id;

    @Column(nullable = false)
    private int xval;

    @Column
    private String text;

    @ManyToOne
    @JoinColumn(name="gapNode_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private GapNode gapNode;

    public GapStone(int xval, String text, GapNode gapNode) {
        this.xval = xval;
        this.text = text;
        this.gapNode = gapNode;
    }

    public void update(GapStoneRequestDto requestDto) {
        this.xval = requestDto.getXval();
        this.text = requestDto.getText();
    }
}
