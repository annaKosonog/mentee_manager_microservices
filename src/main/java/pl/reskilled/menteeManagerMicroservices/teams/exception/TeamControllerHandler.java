package pl.reskilled.menteeManagerMicroservices.teams.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class TeamControllerHandler {


    @ExceptionHandler(ProjectNameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public TeamErrorResponse projectNotFound(ProjectNameNotFoundException exception) {
        final String message = "Project with name not found:   " + exception.getName();
        log.warn(message);
        return new TeamErrorResponse(HttpStatus.NOT_FOUND, message);
    }

    @ExceptionHandler(MenteeEmailNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public TeamErrorResponse menteeNotFound(MenteeEmailNotFoundException exception) {
        final String message = "Mentee with email not found: " + exception.getName();
        log.warn(message);
        return new TeamErrorResponse(HttpStatus.NOT_FOUND, message);
    }
}
