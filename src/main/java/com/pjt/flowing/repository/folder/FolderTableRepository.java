package com.pjt.flowing.repository.folder;

import com.pjt.flowing.model.folder.FolderTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FolderTableRepository extends JpaRepository<FolderTable,Long> {
    List<FolderTable> findAllByMember_Id(Long userId);
}
