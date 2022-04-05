package com.pjt.flowing.model.gapnode;

import com.pjt.flowing.dto.request.gapnode.GapNodeEditRequestDto;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class GapNode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="gapNode_id")
    private Long id;

    @Column(nullable = false)
    private String subject;

    @Column
    private String text;

    @Column(nullable = false)
    private String targetText;

    @ManyToOne
    @JoinColumn(name="gapTable_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private GapTable gapTable;

    @OneToMany(mappedBy = "gapNode",fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<GapStone> gapStoneList = new ArrayList<>();

    @Builder
    public GapNode(String subject, String text, String targetText,GapTable gapTable){
        this.subject = subject;
        this.text = text;
        this.targetText = targetText;
        this.gapTable = gapTable;
    }

    public void update(GapNodeEditRequestDto dto){
        this.subject=dto.getSubject();
        this.text=dto.getText();
        this.targetText=dto.getTargetText();
    }
}
