package com.pjt.flowing.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
    public class Bookmark extends Timestamped{

        @Id @GeneratedValue
        private Long id;

        @ManyToOne
        @JoinColumn(name="project_id")
        private Project project;

        @ManyToOne
        @JoinColumn(name="user_id")
        private Member member;

    public Bookmark(Project project, Member member) {
        this.project = project;
        this.member = member;
    }
}