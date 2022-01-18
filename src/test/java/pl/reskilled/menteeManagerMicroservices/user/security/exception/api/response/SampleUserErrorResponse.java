package pl.reskilled.menteeManagerMicroservices.user.security.exception.api.response;

import org.springframework.http.HttpStatus;
import pl.reskilled.menteeManagerMicroservices.user.security.exception.AuthErrorResponse;

public interface SampleUserErrorResponse {
    default AuthErrorResponse sampleUserErrorResponse(){
        return new AuthErrorResponse(HttpStatus.CONFLICT, "Error: Email is already taken!!!");
    }
}
