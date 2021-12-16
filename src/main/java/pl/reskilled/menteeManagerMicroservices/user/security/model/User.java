package pl.reskilled.menteeManagerMicroservices.user.security.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;


@Data
@Document(collection = "user")
@Builder
public class User {
    @Id
    private String id;

    private String username;

    @Email
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String email;

    private String password;


    private Set<Authority> authorities = new HashSet<>();


    public User(String username, String email, String password, Set<Authority> authorities) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public User(@Email String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User() {
    }
}
