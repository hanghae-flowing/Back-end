package com.pjt.flowing.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id //pk값으로 쓰겠다
    @Column(name = "userId")
    private Long id;

    @Column(nullable = false, unique = true)
    private Long kakaoId;

    @Column(unique = true)  //선택사항 이기때문에 nullable=true(default)
    private String email;

    @Column(nullable = false) // 카카오 닉네임은 중복 될 수 있다.
    private String nickname;

    @Column()
    private String profileImageURL;

    public Member(Long kakaoId,String email, String nickname, String profileImageURL) {
        this.kakaoId = kakaoId;
        this.email = email;
        this.nickname = nickname;
        this.profileImageURL=profileImageURL;
    }
}