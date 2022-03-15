package pl.reskilled.menteeManagerMicroservices.project.domain;

import pl.reskilled.menteeManagerMicroservices.project.domain.dto.ProjectDto;

import java.util.HashSet;
import java.util.Set;

public class ProjectUtil {

    public static Project allParametersForProject(String id, String name, Set<String> developers, Set<String> techStack, String description) {
        return new Project(id, name, developers, techStack, description);
    }

    public static Project allParametersWithoutId(String name, Set<String> developers, Set<String> techStack, String description) {
        return new Project(null, name, developers, techStack, description);
    }

    public static ProjectDto allParameterProjectDto(String name, Set<String> developers, Set<String> techStack, String description) {
        return new ProjectDto(name, developers, techStack, description);
    }

    public static Set<String> developerSet() {
        Set<String> developerSet = new HashSet<>();
        developerSet.add("Jan Kowalski");
        developerSet.add("Nikodem Smieszek");
        return developerSet;
    }

    public static Set<String> techStackSet() {
        Set<String> techStackSet = new HashSet<>();
        techStackSet.add("Java");
        techStackSet.add("Spring_boot");
        techStackSet.add("MongoDb");
        return techStackSet;
    }

    public static Project firstProjectWithoutId() {
        return allParametersWithoutId("Secret keys", developerSet(), techStackSet(), "User write application");
    }

    public static ProjectDto addNewProject() {
        return allParameterProjectDto("Secret keys", developerSet(), techStackSet(), "User write application");
    }


    public static Project secretKey() {
        return allParametersForProject("61e98e0b825ad234e3725cca", "Secret keys", developerSet(), techStackSet(), "User second");
    }

    public static Project pacman() {
        return allParametersForProject("24ee32b6-6b15-11eb-9439-0242ac130002",
                "Pacman_Game",
                developerSet(),
                techStackSet(),
                "RPG_GAME"
        );
    }



    public static ProjectDto secondNewProjectDto() {
        return allParameterProjectDto("Secret keys", developerSet(), techStackSet(), "User write application");
    }


    public static ProjectDto secretKeyDtoMapper() {
        return allParameterProjectDto("Secret keys", developerSet(), techStackSet(), "User second");
    }

    public static ProjectDto pacmanDto() {
        return allParameterProjectDto("Pacman_Game",
                developerSet(),
                techStackSet(),
                "RPG_GAME"
        );
    }
    public static Project pacmanDao(){
    return allParametersForProject(
            "123dsa", "Pacman_Game",
            developerSet(),
            techStackSet(),
            "RPG_GAME"
    );
    }
}

