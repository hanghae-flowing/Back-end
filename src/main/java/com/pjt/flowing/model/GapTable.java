package com.pjt.flowing.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class GapTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="gapTable_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;

    public GapTable(Project project) {
        this.project = project;
    }
}
