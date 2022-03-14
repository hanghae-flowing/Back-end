package com.pjt.flowing.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ProjectMember {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="userId")
    private Member member;

    @ManyToOne
    @JoinColumn(name="projectId")
    private Project project;
}
