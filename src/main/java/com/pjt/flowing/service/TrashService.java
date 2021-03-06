package com.pjt.flowing.service;

import com.pjt.flowing.dto.request.invite.AcceptRequestDto;
import com.pjt.flowing.dto.request.project.ProjectIdDeleteRequestDto;
import com.pjt.flowing.dto.response.project.ProjectResponseDto;
import com.pjt.flowing.model.project.Project;
import com.pjt.flowing.model.project.ProjectMember;
import com.pjt.flowing.repository.project.ProjectMemberRepository;
import com.pjt.flowing.repository.project.ProjectRepository;
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
            }
            else if (project.isTrash()) {
                project.setTrash(false);
                obj.put("복구합니다.(project trash 상태)", false);
            }
        }
        else {
            projectMemberRepository.deleteByMember_IdAndProject_Id(requestDto.getUserId(), requestDto.getProjectId());
            obj.put("당신은 프로젝트 멤버에서 삭제됩니다.", true);
        }
        return obj.toString();
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

    @Transactional
    public String trashDeleteAll(Long userId) {
        projectRepository.deleteAllByMember_IdAndTrash(userId, true);
        JSONObject obj = new JSONObject();
        obj.put("msg", "휴지통을 비웁니다.");

        return obj.toString();
    }

    @Transactional
    public String choiceDelete(ProjectIdDeleteRequestDto requestDto) {
        requestDto.getProjectIdList().stream()
                .forEach(s -> projectRepository.deleteById(s));
        JSONObject obj = new JSONObject();
        obj.put("msg", "선택 삭제 완료!");
        return obj.toString();
    }

    @Transactional
    public String restoreProject(ProjectIdDeleteRequestDto requestDto) {
        for (Long projectId : requestDto.getProjectIdList()) {
            Project project = projectRepository.findById(projectId).orElseThrow(
                    () -> new IllegalArgumentException("func/ restoreProject/ projectId")
            );
            project.setTrash(false);
        }
        JSONObject obj = new JSONObject();
        obj.put("msg", "선택한 프로젝트가 복구되었습니다.");
        return obj.toString();
    }
}
