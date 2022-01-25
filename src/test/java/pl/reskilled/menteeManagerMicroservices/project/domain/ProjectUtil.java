package pl.reskilled.menteeManagerMicroservices.project.domain;

import pl.reskilled.menteeManagerMicroservices.project.domain.dto.ProjectDto;

import java.util.HashSet;
import java.util.Set;

public class ProjectUtil {

    public static Project allParametersForProject(String id, String name, Set<String> developers, Set<String> techStack, String description) {
        return new Project(id, name, developers, techStack, description);
    }

    public static Project allParametersWithoutId(String name, Set<String> developers, Set<String> techStack, String description) {
        return Project.builder()
                .name(name)
                .developers(developers)
                .techStack(techStack)
                .description(description)
                .build();
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
        techStackSet.add("Spring boot");
        techStackSet.add("MongoDb");
        return techStackSet;
    }
}
