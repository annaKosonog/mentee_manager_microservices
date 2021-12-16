package pl.reskilled.menteeManagerMicroservices.user.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.reskilled.menteeManagerMicroservices.user.security.model.valid.UserRoleValid;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class SignUpDto {

    @NotBlank(message = "{username.not.blank}")
    private String username;
    @Email
    @NotBlank(message = "{email.not.blank}")
    private String email;
    @NotBlank(message = "{password.not.blank}")
    private String password;

    @UserRoleValid(targetClassType = Authority.class)
    private Set<Authority> authorities = new HashSet<>();
}
