package pl.reskilled.menteeManagerMicroservices.project.exception.response;

import lombok.Getter;

@Getter
public class NameExistsException extends RuntimeException {
    private static final long serialVersionUID = -4856846361193249489L;
    private final String info;

    public NameExistsException(String name) {
        super("The name is exists to the db: " + name);
        this.info = name;
    }
}
