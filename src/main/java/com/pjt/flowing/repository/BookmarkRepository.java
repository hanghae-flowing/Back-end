package com.pjt.flowing.repository;

import com.pjt.flowing.model.Bookmark;
import com.pjt.flowing.model.Member;
import com.pjt.flowing.model.Project;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    boolean existsByMember_IdAndProject_Id(Long userId, Long projectId);
    void deleteByMember_IdAndProject_Id(Long userId, Long projectId);
}
