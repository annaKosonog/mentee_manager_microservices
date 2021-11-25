package pl.reskilled.menteeManagerMicroservices.user.exception.api.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserControllerErrorHandlerTest implements SampleUserErrorResponse, SampleUserExistsByUsername {

    @Test
    public void should_return_user_error_conflict() {
        final String email = "wacek@wp.pl";

        UserControllerErrorHandler userControllerErrorHandler = new UserControllerErrorHandler();
        final UserExistEmailException exception = sampleUserExistsByUsername(email);
        final UserErrorResponse expectedResponse = sampleUserErrorResponse();

        final UserErrorResponse actualResponse = userControllerErrorHandler.userConflict(exception);

        assertThat(actualResponse.equals(expectedResponse));

    }
}
