package pl.reskilled.menteeManagerMicroservices.user.model;

import org.springframework.http.ResponseEntity;
import pl.reskilled.menteeManagerMicroservices.user.security.MessageResponse;

public interface SampleMessageResponse {
    default ResponseEntity<MessageResponse> okMessageResponse(String message){
        return ResponseEntity.ok(new MessageResponse(message));
    }
}
