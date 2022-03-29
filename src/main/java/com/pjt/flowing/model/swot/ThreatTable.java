package com.pjt.flowing.model.swot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private SWOT swot;

    public ThreatTable(String text, SWOT swot) {
        this.text = text;
        this.swot = swot;
    }
}
