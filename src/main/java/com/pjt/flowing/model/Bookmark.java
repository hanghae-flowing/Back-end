package com.pjt.flowing.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Bookmark extends Timestamped{

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="projectId")
    private Project project;

    @ManyToOne
    @JoinColumn(name="kakaoId")
    private Member member;

}
