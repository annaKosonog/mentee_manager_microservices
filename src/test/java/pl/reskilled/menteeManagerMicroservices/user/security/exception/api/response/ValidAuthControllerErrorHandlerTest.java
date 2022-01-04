package pl.reskilled.menteeManagerMicroservices.user.security.exception.api.response;

import org.junit.jupiter.api.Test;
import pl.reskilled.menteeManagerMicroservices.user.exception.api.response.UserExistEmailException;
import pl.reskilled.menteeManagerMicroservices.user.security.exception.AuthControllerErrorHandler;
import pl.reskilled.menteeManagerMicroservices.user.security.exception.AuthErrorResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidAuthControllerErrorHandlerTest implements SampleUserErrorResponse, SampleUserExistsByEmail {

    @Test
    public void should_return_user_error_conflict() {
        final String email = "wacek@wp.pl";

        AuthControllerErrorHandler authControllerErrorHandler = new AuthControllerErrorHandler();
        final UserExistEmailException exception = sampleUserExistsByEmail(email);
        final AuthErrorResponse expectedResponse = sampleUserErrorResponse();

        final AuthErrorResponse actualResponse = authControllerErrorHandler.userConflict(exception);

        assertThat(actualResponse.equals(expectedResponse));

    }
}
