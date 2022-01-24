package pl.reskilled.menteeManagerMicroservices.project.domain;

import pl.reskilled.menteeManagerMicroservices.project.domain.dto.ProjectDto;

import java.util.HashSet;
import java.util.Set;

public interface SampleProjectDto {

    default ProjectDto allParameterProjectDto(String name, Set<String> developers, Set<String> techStack, String description) {
        return new ProjectDto(name, developers, techStack, description);
    }

    default ProjectDto addNewProject() {
        return allParameterProjectDto("Secret keys", developerSet(), techStackSet(), "User write application");
    }

    default Set<String> developerSet() {
        Set<String> developerSet = new HashSet<>();
        developerSet.add("Jan Kowalski");
        developerSet.add("Nikodem Smieszek");
        return developerSet;
    }

    default ProjectDto secondNewProjectDto() {
        return allParameterProjectDto("Secret keys", developerSet(), techStackSet(), "User write application");
    }

    default Set<String> techStackSet() {
        Set<String> techStackSet = new HashSet<>();
        techStackSet.add("Java");
        techStackSet.add("Spring boot");
        techStackSet.add("MongoDb");
        return techStackSet;
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
