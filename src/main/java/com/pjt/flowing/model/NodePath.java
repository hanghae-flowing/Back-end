package com.pjt.flowing.model;

import com.pjt.flowing.dto.request.NodePathRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class NodePath {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="node_path_id")
    private Long id;

    @Column(nullable = false)
    private Long parentNode;

    @Column(nullable = false)
    private Long childNode;

    @ManyToOne
    @JoinColumn(name="nodeTable_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private NodeTable nodeTable;

    public NodePath(Long parentNode, Long childNode, NodeTable nodeTable) {
        this.parentNode = parentNode;
        this.childNode = childNode;
        this.nodeTable = nodeTable;
    }
}
