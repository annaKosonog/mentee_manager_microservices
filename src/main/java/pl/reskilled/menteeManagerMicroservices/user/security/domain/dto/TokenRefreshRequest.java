package pl.reskilled.menteeManagerMicroservices.user.security.domain.dto;

import javax.validation.constraints.NotBlank;

public class TokenRefreshRequest {

    @NotBlank
    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }
}
