package com.pjt.flowing.model.swot;

import com.pjt.flowing.model.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StrengthTable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="strength_id")
    private Long id;

    @Column(length=300)
    private String text;

    @ManyToOne
    @JoinColumn(name="swot_id")
    private SWOT swot;


    public StrengthTable(String text, SWOT swot) {
        this.text = text;
        this.swot = swot;
    }
}
