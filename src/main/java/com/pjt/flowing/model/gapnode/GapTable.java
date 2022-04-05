package com.pjt.flowing.model.gapnode;

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
public class GapTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="gapTable_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="project_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Project project;

    @OneToMany(mappedBy = "gapTable",fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<GapNode> gapNodeList = new ArrayList<>();

    public GapTable(Project project) {
        this.project = project;
    }
}
