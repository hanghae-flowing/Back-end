package com.pjt.flowing.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class NodeTable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="nodeTable_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;

    public NodeTable(Project project) {
        this.project = project;
    }
}
