package com.pjt.flowing.model;

import com.pjt.flowing.dto.ProjectEditRequestDto;
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

    @Column
    private Long objectId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private Member member;

    @Column(nullable = false)
    private int thumbNailNum;

    @OneToMany(mappedBy = "project",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Bookmark> BookmarkList = new ArrayList<>();

    @OneToMany(mappedBy = "project",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ProjectMember> ProjectMemberList = new ArrayList<>();

    @OneToMany(mappedBy = "project",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Node> NodeList = new ArrayList<>();    //node 단방향 매핑

    public Project(String projectName, Long objectId, Member member, int thumbNailNum) {
        this.projectName = projectName;
        this.objectId = objectId;
        this.member = member;   //여기서의 member는 프로젝트 생성자를 말한다.
        this.thumbNailNum = thumbNailNum;
    }

    public void update(ProjectEditRequestDto dto){
        this.projectName=dto.getProjectName();
        this.thumbNailNum=dto.getThumbNailNum();
    }

}
