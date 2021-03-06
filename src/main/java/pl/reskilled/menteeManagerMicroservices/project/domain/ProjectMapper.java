package pl.reskilled.menteeManagerMicroservices.project.domain;

import org.springframework.stereotype.Component;
import pl.reskilled.menteeManagerMicroservices.project.domain.dto.ProjectDto;

@Component
public class ProjectMapper {

    public ProjectDto mapToProjectDto(Project project) {
        return ProjectDto.builder()
                .name(project.getName())
                .developers(project.getDevelopers())
                .techStack(project.getTechStack())
                .description(project.getDescription())
                .build();
    }

    public Project mapToProject(ProjectDto projectDto) {

        Project project = new Project();
        project.setName(projectDto.getName());
        project.setDevelopers(projectDto.getDevelopers());
        project.setTechStack(projectDto.getTechStack());
        project.setDescription(projectDto.getDescription());
        return project;
    }
}
