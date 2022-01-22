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


    default Project firstProject() {
        return allParametersForProject("61e98e0b825ad234e3725cca", "Secret keys", developerSet(), techStackSet(), "User write application");
    }


}
