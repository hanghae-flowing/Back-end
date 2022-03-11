package com.pjt.flowing.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Project extends Timestamped{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="project_id")
    private Long id;

    @Column(nullable = false)
    private String projectName;

    @Column(nullable = false)
    private Long objectId;

    @ManyToOne
    @JoinColumn(name="kakao_id")
    private Member member;

    @Column(nullable = false)
    private int thumbNailNum;

    @OneToMany(mappedBy = "project",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Bookmark> BookmarkList = new ArrayList<>();

    @OneToMany(mappedBy = "project",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProjectMember> ProjectMemberList = new ArrayList<>();
}
