package com.pjt.flowing.model;

import com.pjt.flowing.model.project.Project;
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
public class Member extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name="user_id")
    private Long id;

    @Column(name="kakao_id",nullable = false)
    private Long kakaoId;

    @Column(unique = true)
    private String email;

    @Column(nullable = false) // 카카오 닉네임은 중복 될 수 있다.
    private String nickname;

    @Column
    private String profileImageURL;

    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Project> projectList = new ArrayList<>();

    public Member(Long kakaoId,String email, String nickname, String profileImageURL) {
        this.kakaoId = kakaoId;
        this.email = email;
        this.nickname = nickname;
        this.profileImageURL=profileImageURL;
    }
}