package pl.reskilled.menteeManagerMicroservices.project.domain;

import java.util.Set;

public interface SampleProject extends SampleProjectDto {
    default Project allParametersForProject(String id, String name, Set<String> developers, Set<String> techStack, String description) {
        return new Project(id, name, developers, techStack, description);
    }

    default Project allParametersWithoutId(String name, Set<String> developers, Set<String> techStack, String description) {
        return Project.builder()
                .name(name)
                .developers(developers)
                .techStack(techStack)
                .description(description)
                .build();
    }

    default Project firstProjectWithoutId() {
        return allParametersWithoutId("Secret keys", developerSet(), techStackSet(), "User write application");
    }


    default Project secretKey() {
        return allParametersForProject("61e98e0b825ad234e3725cca", "Secret keys", developerSet(), techStackSet(), "User second");
    }

    default Project pacman(){
        return allParametersForProject("24ee32b6-6b15-11eb-9439-0242ac130002",
                "Pacman Game",
                developerSet(),
                techStackSet(),
                "RPG game"
                );
    }


}
