package pl.reskilled.menteeManagerMicroservices.project.domain;

import org.springframework.stereotype.Component;
import pl.reskilled.menteeManagerMicroservices.project.domain.dto.ProjectDto;

@Component
public class ProjectMapper {

    public static ProjectDto mapToProjectDto(Project project){
        return ProjectDto.builder()
                .name(project.getName())
                .developers(project.getDevelopers())
                .techStack(project.getTechStack())
                .description(project.getDescription())
                .build();
    }

    public static Project mapToProject(ProjectDto projectDto){
        return Project.builder()
                .name(projectDto.getName())
                .developers(projectDto.getDevelopers())
                .techStack(projectDto.getTechStack())
                .description(projectDto.getDescription())
                .build();
    }
}
