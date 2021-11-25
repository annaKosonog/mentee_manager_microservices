package pl.reskilled.menteeManagerMicroservices.user.exception.api.response;

import lombok.Getter;

@Getter
public class UserExistEmailException extends RuntimeException {

    private static final long serialVersionUID = -4856846361193249489L;
    private final String info;

    public UserExistEmailException(String email) {
        super(String.format("The given url already exists in the database: " + "\n" + email));
        this.info = email;
    }
}