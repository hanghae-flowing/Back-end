package com.pjt.flowing.repository;

import com.pjt.flowing.model.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<Folder,Long> {
    void deleteByFolderTable_IdAndAndProjectId(Long folderTableId,Long projectId);
}
