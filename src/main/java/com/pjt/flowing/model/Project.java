package com.pjt.flowing.model;

import com.pjt.flowing.dto.request.ProjectEditRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private Member member; // 생성한사람...

    @Column(nullable = false)
    private int thumbNailNum;

    @Column(nullable = false)
    private boolean trash;

    @OneToMany(mappedBy = "project",fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Bookmark> BookmarkList = new ArrayList<>();

    @OneToMany(mappedBy = "project",fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<ProjectMember> ProjectMemberList = new ArrayList<>();

    @OneToMany(mappedBy = "project",fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<NodeTable> NodeTableList = new ArrayList<>();

    @OneToMany(mappedBy = "project",fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<GapTable> GapTableList = new ArrayList<>();

    @OneToMany(mappedBy = "project",fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Document> DocumentList = new ArrayList<>();

    public Project(String projectName, Long objectId, Member member, int thumbNailNum) {
        this.projectName = projectName;
        this.objectId = objectId;
        this.member = member;   //여기서의 member는 프로젝트 생성자를 말한다.
        this.thumbNailNum = thumbNailNum;
        this.trash = false;
<<<<<<< refs/remotes/origin/feature-121
=======

>>>>>>> feat: 프로젝트 조회/생성 휴지통인지 확인
    }

    public void update(ProjectEditRequestDto dto){
        this.projectName=dto.getProjectName();
        this.thumbNailNum=dto.getThumbNailNum();
    }

    public void setTrash(boolean trash) {
        this.trash = trash;
    }

}
