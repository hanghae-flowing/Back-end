package com.pjt.flowing.model.project;

import com.pjt.flowing.model.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Setter
public class ProjectMember {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;

    public ProjectMember(Project project, Member member) {
        this.project = project;
        this.member = member;
    }

}
