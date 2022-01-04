package pl.reskilled.menteeManagerMicroservices.user.security.domain;

import org.springframework.http.ResponseEntity;
import pl.reskilled.menteeManagerMicroservices.user.security.payload.response.JwtResponse;

import java.util.Set;

public interface SampleJwtResponse {
    default ResponseEntity<JwtResponse> sampleJwtResponse(String token, String refreshToken, String email, Set<String> roles){
        return ResponseEntity.ok(new JwtResponse(token,refreshToken,  email, roles));
    }
}
