package com.pjt.flowing.repository;

import com.pjt.flowing.model.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FolderRepository extends JpaRepository<Folder,Long> {
    void deleteByFolderTable_IdAndAndProjectId(Long folderTableId,Long projectId);
    List<Folder> findAllByFolderTable_Id(Long folderTableId);
    boolean existsByFolderTable_idAndProjectId(Long folderTableId,Long projectId);
}
