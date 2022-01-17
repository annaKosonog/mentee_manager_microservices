package pl.reskilled.menteeManagerMicroservices.user.security.payload.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Set;

@ToString
@Getter
@RequiredArgsConstructor
public class JwtResponse {

    private final String token;

    private String type = "Bearer";

    private final  String refreshToken;

    private final String id;

    private final  String username;

    private final  String email;

    private final  Set<String> roles;

}
