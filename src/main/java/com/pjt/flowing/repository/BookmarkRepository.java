package com.pjt.flowing.repository;
import com.pjt.flowing.model.Bookmark;
import com.pjt.flowing.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    boolean existsByMember_IdAndProject_Id(Long userId, Long projectId);
    void deleteByMember_IdAndProject_Id(Long userId, Long projectId);

    //List<Bookmark> findAllByMember_IdOrderByModifiedAtDesc(Long userId);
    List<Bookmark> findAllByMember_IdOrderByModifiedAtDesc(Long UserId);



}
