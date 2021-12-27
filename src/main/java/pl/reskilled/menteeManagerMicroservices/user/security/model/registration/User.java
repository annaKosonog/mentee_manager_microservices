package pl.reskilled.menteeManagerMicroservices.user.security.model.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.reskilled.menteeManagerMicroservices.user.security.model.objectValidation.UserRoleValid;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@Document(collection = "user")
public class User {
    @Id
    private String id;

    @NotBlank
    private String username;

    @Email
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @UserRoleValid(anyOf = {Authority.STUDENT, Authority.MENTOR})
    private Set<Authority> authorities = new HashSet<>();


    public User(String username, String email, String password, Set<Authority> authorities) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public User(@Email String email, String password, Set<Authority> authorities) {
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }
}
