package pl.reskilled.menteeManagerMicroservices.user.security.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import pl.reskilled.menteeManagerMicroservices.user.security.exception.TokenRefreshException;

import java.util.Date;

@ControllerAdvice
public class TokenControllerAdvice {

  @ExceptionHandler(value = TokenRefreshException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public ErrorMessage handleTokenRefreshException(TokenRefreshException ex, WebRequest request) {
    return new ErrorMessage(
        HttpStatus.FORBIDDEN.value(),
        new Date(),
        ex.getMessage(),
        request.getDescription(false));
  }
}
