package pl.reskilled.menteeManagerMicroservices.user.security.payload.response;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TokenRefreshResponse {
    private final String accessToken;
    private final String refreshToken;
    private String tokenType = "Bearer";
}
