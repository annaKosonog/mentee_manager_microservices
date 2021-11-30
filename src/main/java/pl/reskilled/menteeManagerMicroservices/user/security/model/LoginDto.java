package pl.reskilled.menteeManagerMicroservices.user.security.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class LoginDto {
    @NotBlank(message = "{email.not.blank}")
    private String email;
    @NotBlank(message = "{password.not.blank}")
    private String password;
}

