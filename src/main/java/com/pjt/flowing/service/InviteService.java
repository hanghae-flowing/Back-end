package com.pjt.flowing.service;

import com.pjt.flowing.dto.request.invite.InviteRequestDto;

import com.pjt.flowing.dto.response.invite.InviteResponseDto;
import com.pjt.flowing.model.InviteTable;
import com.pjt.flowing.model.Member;
import com.pjt.flowing.model.project.Project;
import com.pjt.flowing.model.project.ProjectMember;
import com.pjt.flowing.repository.InviteRepository;
import com.pjt.flowing.repository.MemberRepository;
import com.pjt.flowing.repository.project.ProjectMemberRepository;
import com.pjt.flowing.repository.project.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class InviteService {

    private final InviteRepository inviteRepository;
    private final MemberRepository memberRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectRepository projectRepository;

    @Transactional
    public String inviteMember(InviteRequestDto inviteRequestDto){
        JSONObject obj = new JSONObject();
        Member invitingMember = memberRepository.findById(inviteRequestDto.getUserId()).orElseThrow(
                ()->new IllegalArgumentException("func/ inviteMember/ error inviting userId")
        );

        Member invitedMember = memberRepository.findByEmail(inviteRequestDto.getEmail()).orElseThrow(
                ()->new IllegalArgumentException("func/ inviteMember/ error invited user email")
        );

        Project project = projectRepository.findById(inviteRequestDto.getProjectId()).orElseThrow(
                ()->new IllegalArgumentException("func/ inviteMember/ error projectid")
        );

        InviteTable inviteTable = new InviteTable(project,invitingMember,invitedMember);
        if(projectMemberRepository.existsByMember_EmailAndProject_Id(inviteRequestDto.getEmail(),inviteRequestDto.getProjectId())){
            obj.put("msg","이미 초대 있어");
        }
        else{
            inviteRepository.save(inviteTable);
            obj.put("msg","초대완료");
        }
        return obj.toString();
    }

    public String getInviting(Long userId){
        List<InviteTable> inviteList = inviteRepository.findAllByInvitedmember_Id(userId);//userid로 찾아오기
        List<InviteResponseDto> responseDtoList = new ArrayList<>();
        for(InviteTable inviteTable : inviteList){
            InviteResponseDto responseDto = new InviteResponseDto(inviteTable.getId(),inviteTable.getInvitingMember().getNickname(),inviteTable.getProject().getProjectName(),inviteTable.getModifiedAt(),inviteTable.getInvitingMember().getProfileImageURL());
            responseDtoList.add(responseDto);
        }//inviteTableid,초대한사람,초대 받은 프로젝트 보내주기
        JSONArray jsonArray = new JSONArray(responseDtoList);
        return jsonArray.toString();

    }

    //초대수락
    public String accept(Long invitingId){
        JSONObject obj = new JSONObject();
        InviteTable inviteTable = inviteRepository.findById(invitingId).orElseThrow(
                ()-> new IllegalArgumentException("func/ accept / error inviting id")
        );

        ProjectMember projectMember= new ProjectMember(inviteTable.getProject(),inviteTable.getInvitedMember());
        projectMemberRepository.save(projectMember);

        inviteRepository.delete(inviteTable);

        obj.put("msg","초대 수락");
        return obj.toString();
    }

    //초대 거절
    public String refuse(Long invitingId){
        JSONObject obj = new JSONObject();
        InviteTable inviteTable = inviteRepository.findById(invitingId).orElseThrow(
                ()-> new IllegalArgumentException("func/ refuse / error inviting id")
        );
        inviteRepository.delete(inviteTable);
        obj.put("msg","초대 거절");
        return obj.toString();
    }

}
