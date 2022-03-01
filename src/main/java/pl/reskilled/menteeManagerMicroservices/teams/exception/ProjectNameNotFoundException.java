package pl.reskilled.menteeManagerMicroservices.teams.exception;

import lombok.Getter;

@Getter
public class ProjectNameNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -6378244667204645097L;
    private final String name;

    public ProjectNameNotFoundException(String name) {
        super(String.format("Project with name %s not found", name));
        this.name = name;
    }
}
