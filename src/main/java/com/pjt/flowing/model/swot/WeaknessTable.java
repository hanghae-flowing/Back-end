package com.pjt.flowing.model.swot;

import com.pjt.flowing.dto.request.SWOTRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WeaknessTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="weakness_id")
    private Long id;

    @Column(length=300)
    private String text;

    @ManyToOne
    @JoinColumn(name="swot_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SWOT swot;

    public WeaknessTable(String text, SWOT swot) {
        this.text = text;
        this.swot = swot;
    }

    public void weaknessUpdate(SWOTRequestDto requestDto) {
        this.text = requestDto.getText();
    }
}
