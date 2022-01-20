package pl.reskilled.menteeManagerMicroservices.project.exception.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class ProjectErrorResponse {

    public final HttpStatus status;
    public final String message;
}
