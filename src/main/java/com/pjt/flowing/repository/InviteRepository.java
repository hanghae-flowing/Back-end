package com.pjt.flowing.repository;

import com.pjt.flowing.model.InviteTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InviteRepository extends JpaRepository<InviteTable,Long> {
    List<InviteTable> findAllByInvitedmember_Id(Long userId);
}
