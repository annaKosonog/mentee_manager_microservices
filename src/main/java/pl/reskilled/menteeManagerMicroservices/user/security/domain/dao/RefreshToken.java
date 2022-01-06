package pl.reskilled.menteeManagerMicroservices.user.security.domain.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Document(collection = "refreshtoken")
public class RefreshToken {

    @Id
    private String id;

    @NotBlank
    private User user;

    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    @NotBlank
    private String token;

    @NotBlank
    private Instant expiryDate;
}
