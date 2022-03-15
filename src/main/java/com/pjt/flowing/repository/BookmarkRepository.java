package com.pjt.flowing.repository;

import com.pjt.flowing.model.Bookmark;
import com.pjt.flowing.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    //List<Bookmark> findAllByMember_IdOrderByModifiedAtDesc(Long userId);
    List<Project> findAllByMember_IdOrderByModifiedAtDesc(Long UserId);
}
