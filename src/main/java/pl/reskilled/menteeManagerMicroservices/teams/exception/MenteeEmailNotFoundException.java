package pl.reskilled.menteeManagerMicroservices.teams.exception;

import lombok.Getter;

@Getter
public class MenteeEmailNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -6378244667204645097L;
    private final String name;

    public MenteeEmailNotFoundException(String email) {
        super(String.format("Mentee with email %s not found", email));
        this.name = email;
    }
}
