package pl.reskilled.menteeManagerMicroservices.user.exception.api.response;

public interface SampleUserExistsByEmail {

    default UserExistEmailException sampleUserExistsByEmail(String email){
        return new UserExistEmailException(email);
    }
}
