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
    @Column(name="project_id")
    private Long id;

    @Column(nullable = false)
    private String projectName;

    // 일단 보류!!!
//    @Column(nullable = false)
//    private Long objectId;

    @ManyToOne
    @JoinColumn(name="kakao_id")
    private Member member;

    @Column(nullable = false)
    private int thumbNailNum;

    @OneToMany(mappedBy = "project",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Bookmark> BookmarkList = new ArrayList<>();

    @OneToMany(mappedBy = "project",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProjectMember> ProjectMemberList = new ArrayList<>();

    public Project(String projectName, Member member, int thumbNailNum) {
        this.projectName = projectName;
        this.member = member;
        this.thumbNailNum = thumbNailNum;
    }
}
