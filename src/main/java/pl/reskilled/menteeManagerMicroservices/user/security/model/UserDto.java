package pl.reskilled.menteeManagerMicroservices.user.security.model;

import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@EqualsAndHashCode
@Getter
@Setter
public class UserDto {

    private String name;
    private String email;
    private String password;
    @Ignore
    private String confirmPassword;

    public UserDto(String name, String email, String password, String confirmPassword) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public UserDto() {
    }

    public UserDto(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
