package pl.reskilled.menteeManagerMicroservices.user.security.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@EqualsAndHashCode
@Getter
@Setter
public class LoginRequestDto {

    private String name;
    private String email;
    private String password;


    public LoginRequestDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public LoginRequestDto() {
    }
}
