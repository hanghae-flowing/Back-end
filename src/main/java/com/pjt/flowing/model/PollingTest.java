package com.pjt.flowing.model;


import com.pjt.flowing.dto.PollingEditDto;
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
    @Column(name="project_id")
    private Long id;

    @Column
    private String text;

    public PollingTest(String text) {
        this.text = text;
    }

    public void update(PollingEditDto editDto){
        this.text=editDto.getText();
    }

}
