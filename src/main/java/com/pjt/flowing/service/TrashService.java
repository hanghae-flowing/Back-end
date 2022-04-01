package com.pjt.flowing.service;

import com.pjt.flowing.dto.request.AcceptRequestDto;
import com.pjt.flowing.dto.response.ProjectResponseDto;
import com.pjt.flowing.model.Project;
import com.pjt.flowing.model.ProjectMember;
import com.pjt.flowing.repository.ProjectMemberRepository;
import com.pjt.flowing.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TrashService {

    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;

    @Transactional
    public String trashProject(AcceptRequestDto requestDto) {
        boolean projectCheck = projectRepository.existsByMember_IdAndId(requestDto.getUserId(), requestDto.getProjectId());
        boolean projectMemberCheck = projectMemberRepository.existsByMember_IdAndProject_Id(requestDto.getUserId(), requestDto.getProjectId());

        JSONObject obj = new JSONObject();
        if (projectCheck && projectMemberCheck) {
            Project project = projectRepository.findByMember_IdAndId(requestDto.getUserId(), requestDto.getProjectId());
            if (!project.isTrash()) {
                project.setTrash(true);
                obj.put("휴지통으로 보냅니다. (project trash 상태)", true);
                return obj.toString();
            }
            else if (project.isTrash()) {
                project.setTrash(false);
                obj.put("복구합니다.(project trash 상태)", false);
                return obj.toString();
            }
        }
        if ((!projectCheck) && projectMemberCheck) {
            projectMemberRepository.deleteByMember_IdAndProject_Id(requestDto.getUserId(), requestDto.getProjectId());
            obj.put("당신은 프로젝트 멤버에서 삭제됩니다.", true);
            return obj.toString();
        }
        return "아무일도 일어나지 않았습니다.";
    }

    @Transactional
    public List<ProjectResponseDto> showTrash(Long userId) {

        List<ProjectMember> myTrashProjects = projectMemberRepository.findAllByMember_Id(userId); // 자기가 포함된 프로젝트 리스트
        List<ProjectResponseDto> trashDto = myTrashProjects.stream()
                .filter(x -> x.getProject().isTrash())
                .map(ProjectResponseDto::includedProject)
                .sorted(Comparator.comparing(ProjectResponseDto::getModifiedAt).reversed())
                .collect(Collectors.toList());

        return trashDto;
    }
}
