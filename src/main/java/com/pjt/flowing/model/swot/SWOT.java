package com.pjt.flowing.model.swot;

import com.pjt.flowing.model.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class SWOT {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="swot_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public SWOT(Project project) {
        this.project = project;
    }
}
