package com.pjt.flowing.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Folder extends Timestamped {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String folderName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Member member;

    @OneToMany(mappedBy = "folder",fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Project> ProjectList = new ArrayList<>();

    public Folder(String folderName, Member member){
        this.folderName = folderName;
        this.member = member;
    }
}
