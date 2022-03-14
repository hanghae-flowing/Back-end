package com.pjt.flowing.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Project extends Timestamped{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="projectId")
    private Long id;

    @Column(nullable = false)
    private String projectName;

    @Column
    private Long objectId;

    @ManyToOne
    @JoinColumn(name="kakaoId")
    private Member member;

    @Column(nullable = false)
    private int thumbNailNum;

    @OneToMany(mappedBy = "project",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Bookmark> BookmarkList = new ArrayList<>();

    @OneToMany(mappedBy = "project",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProjectMember> ProjectMemberList = new ArrayList<>();

    public Project(String projectName, Long objectId, Member member, int thumbNailNum) {
        this.projectName = projectName;
        this.objectId = objectId;
        this.member = member;   //여기서의 member는 프로젝트 생성자를 말한다.
        this.thumbNailNum = thumbNailNum;
    }

}
