package pl.reskilled.menteeManagerMicroservices.user.exception.api.response;

import org.springframework.http.HttpStatus;

public interface SampleUserErrorResponse {
    default AuthErrorResponse sampleUserErrorResponse(){
        return new AuthErrorResponse(HttpStatus.CONFLICT, "Error: Email is already taken!!!");
    }
}
