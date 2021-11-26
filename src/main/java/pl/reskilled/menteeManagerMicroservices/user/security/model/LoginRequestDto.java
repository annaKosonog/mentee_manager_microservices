package pl.reskilled.menteeManagerMicroservices.user.security.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@ToString
@EqualsAndHashCode
@Getter
@Setter
public class LoginRequestDto {

    @NotBlank(message = "{username.not.blank}")
    private String username;
    @Email
    @NotBlank(message = "{email.not.blank}")
    private String email;
    @NotBlank(message = "{password.not.blank}")
    private String password;


    public LoginRequestDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public LoginRequestDto() {
    }
}
