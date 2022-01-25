package pl.reskilled.menteeManagerMicroservices.project.domain;

import pl.reskilled.menteeManagerMicroservices.project.domain.dto.ProjectDto;

import static pl.reskilled.menteeManagerMicroservices.project.domain.ProjectUtil.allParameterProjectDto;
import static pl.reskilled.menteeManagerMicroservices.project.domain.ProjectUtil.developerSet;
import static pl.reskilled.menteeManagerMicroservices.project.domain.ProjectUtil.techStackSet;

public interface SampleProjectDto {

    default ProjectDto addNewProject() {
        return allParameterProjectDto("Secret keys", developerSet(), techStackSet(), "User write application");
    }

    default ProjectDto secondNewProjectDto() {
        return allParameterProjectDto("Secret keys", developerSet(), techStackSet(), "User write application");
    }



    default ProjectDto secretKeyDtoMapper() {
        return allParameterProjectDto("Secret keys", developerSet(), techStackSet(), "User second");
    }

    default ProjectDto pacmanDtoMapper() {
        return allParameterProjectDto("Pacman Game",
                developerSet(),
                techStackSet(),
                "RPG game"
        );
    }
}
