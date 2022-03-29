package com.pjt.flowing.model.swot;

import com.pjt.flowing.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class SWOT {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="swot_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Project project;

    @OneToMany(mappedBy = "swot",fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<StrengthTable> strengthTableList = new ArrayList<>();

    @OneToMany(mappedBy = "swot",fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<OpportunityTable> opportunityTableList = new ArrayList<>();

    @OneToMany(mappedBy = "swot",fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<WeaknessTable> weaknessTableList = new ArrayList<>();

    @OneToMany(mappedBy = "swot",fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<ThreatTable> threatTableList = new ArrayList<>();


    public SWOT(Project project) {
        this.project = project;
    }
}
