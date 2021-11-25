package pl.reskilled.menteeManagerMicroservices.user.exception.api.response;

import org.springframework.http.HttpStatus;

public interface SampleUserErrorResponse {
    default UserErrorResponse sampleUserErrorResponse(){
        return new UserErrorResponse(HttpStatus.CONFLICT, "Error: Email is already taken!!!");
    }
}
