package pl.reskilled.menteeManagerMicroservices.user.security.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document(collection = "users")
public class User {

    @Id
    private String id;

    private String name;
    @Indexed(unique = true)
    private String email;
    private String password;
    @Field(name = "future_position")
    private String futurePosition;
    private String duration;
    private String intern;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password, String futurePosition, String duration, String intern) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.futurePosition = futurePosition;
        this.duration = duration;
        this.intern = intern;
    }
}
