package pl.reskilled.menteeManagerMicroservices.project.exception.response;

import org.springframework.http.HttpStatus;

public interface SampleProjectErrorResponse {
    default ProjectErrorResponse sampleProjectErrorResponse(){
        return new ProjectErrorResponse(HttpStatus.CONFLICT, " The name is exists to the db: ");
    }
}
