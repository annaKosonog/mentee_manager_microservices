package pl.reskilled.menteeManagerMicroservices.user.exception.api.response;

public interface SampleUserExistsByUsername {

    default UserExistEmailException sampleUserExistsByUsername(String email){
        return new UserExistEmailException(email);
    }
}
