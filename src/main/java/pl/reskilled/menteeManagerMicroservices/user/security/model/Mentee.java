package pl.reskilled.menteeManagerMicroservices.user.security.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Document(collection = "mentees")
public class Mentee {

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

    public Mentee(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Mentee(String name, String email, String futurePosition, String duration, String intern) {
        this.name = name;
        this.email = email;
        this.futurePosition = futurePosition;
        this.duration = duration;
        this.intern = intern;
    }
}
