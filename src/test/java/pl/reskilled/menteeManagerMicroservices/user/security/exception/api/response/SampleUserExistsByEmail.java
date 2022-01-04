package pl.reskilled.menteeManagerMicroservices.user.security.exception.api.response;

import pl.reskilled.menteeManagerMicroservices.user.exception.api.response.UserExistEmailException;

public interface SampleUserExistsByEmail {

    default UserExistEmailException sampleUserExistsByEmail(String email) {
        return new UserExistEmailException(email);
    }
}
