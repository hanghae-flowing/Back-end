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
public class ThreatTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="threat_id")
    private Long id;

    @Column(length=300)
    private String text;

    @ManyToOne
    @JoinColumn(name="swot_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SWOT swot;

    public ThreatTable(String text, SWOT swot) {
        this.text = text;
        this.swot = swot;
    }

    public void threatUpdate(SWOTRequestDto requestDto) {
        this.text = requestDto.getText();
    }
}
