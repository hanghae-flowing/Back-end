package com.pjt.flowing.model;

import com.pjt.flowing.model.project.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class InviteTable extends Timestamped {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="invite_user_id") // 초대하는사람
    private Member invitingmember;

    @ManyToOne
    @JoinColumn(name="invited_user_id") // 초대받는사람
    private Member invitedmember;

    @ManyToOne
    @JoinColumn(name="project_id")  //초대하는 프로젝트
    private Project project;

    public InviteTable(Project project, Member invitingmember, Member invitedmember) {
        this.project = project;
        this.invitingmember = invitingmember;
        this.invitedmember = invitedmember;
    }
}
