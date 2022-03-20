package pl.reskilled.menteeManagerMicroservices.teams.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class MenteeEmailNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -6378244667204645097L;
    private final String name;

    public MenteeEmailNotFoundException(String email) {
        super(String.format("Mentee with email %s not found", email));
        log.error("ERROR: MENTEE WITH GIVEN EMAIL_MENTEE DOES NOT EXIST IN THE DATABASE: " + email);
        this.name = email;
    }
}
