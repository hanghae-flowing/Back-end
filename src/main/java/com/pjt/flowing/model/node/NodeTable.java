package com.pjt.flowing.model.node;

import com.pjt.flowing.model.project.Project;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class NodeTable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="nodeTable_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="project_id")
    @OnDelete(action = OnDeleteAction.CASCADE) //부모쪽에 해줘야 하는 어노테이션
    private Project project;

    @OneToMany(mappedBy = "nodeTable",fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Node> nodeList = new ArrayList<>();

    @OneToMany(mappedBy = "nodeTable",fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<NodePath> nodePathList = new ArrayList<>();

    public NodeTable(Project project) {
        this.project = project;
    }
}
