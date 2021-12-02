package pl.reskilled.menteeManagerMicroservices.user.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "user")
public class User {
    @Id
    private String id;

    private String username;

    @Email
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String email;

    private String password;

    private Set<Authority> roles = new HashSet<>();


    public User(String username, String email, String password, Set<Authority> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User(@Email String email, String password) {
        this.email = email;
        this.password = password;
    }
}
