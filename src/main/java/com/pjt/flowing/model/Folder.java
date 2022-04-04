package com.pjt.flowing.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="folder_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="folderTable_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private FolderTable folderTable;

    @Column
    private Long projectId;

    public Folder(FolderTable folderTable,Long projectId){
        this.folderTable=folderTable;
        this.projectId=projectId;
    }
}
