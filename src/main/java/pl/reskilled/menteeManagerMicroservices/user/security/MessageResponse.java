package pl.reskilled.menteeManagerMicroservices.user.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class MessageResponse {

    private final String message;
}
