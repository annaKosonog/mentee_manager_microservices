package pl.reskilled.menteeManagerMicroservices.project.exception.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SampleProjectControllerHandler implements SampleNameExistsException, SampleProjectErrorResponse {

    @Test
    public void should_return_error_when_name_project_is_duplicate() {
        final String name = "Secret keys";

        ProjectControllerHandler projectControllerHandler = new ProjectControllerHandler();
        final NameExistsException exception = sampleNameExistsException(name);
        final ProjectErrorResponse expectedResponse = sampleProjectErrorResponse();

        final ProjectErrorResponse actualResponse = projectControllerHandler.developerConflict(exception);

        assertThat(actualResponse.equals(expectedResponse));
    }
}
