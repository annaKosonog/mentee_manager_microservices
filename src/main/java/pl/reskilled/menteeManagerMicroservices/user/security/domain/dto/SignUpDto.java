package pl.reskilled.menteeManagerMicroservices.user.security.domain.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.objectValidation.UserRole;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class SignUpDto {

    @NotBlank(message = "{username.not.blank}")
    private String username;

    @Email
    @NotBlank(message = "{email.not.blank}")
    private String email;

    @NotBlank(message = "{password.not.blank}")
    private String password;

    @NotNull(message = "{roles.not.null}")
    @UserRole
    private Set<String> roles;
}
