package com.pjt.flowing.model;


import com.pjt.flowing.dto.PollingEditDto;
import com.pjt.flowing.dto.PollingTestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PollingTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="text_id")
    private Long id;

    @Column(length=1000)
    private String text;

    public PollingTest(String text) {
        this.text = text;
    }

    public void update(PollingEditDto Dto){
        this.text=Dto.getText();
    }

}
