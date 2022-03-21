package pl.reskilled.menteeManagerMicroservices.mentee.domain;

import pl.reskilled.menteeManagerMicroservices.mentee.domain.dao.Duration;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.dao.Mentee;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.dao.Seniority;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.dto.MenteeDto;

public class MenteeUtil {
    public static Mentee allParametersWithMentee(String id, String username, String email, String future_position, Duration duration, Seniority seniority){
        return new Mentee(id, username, email, future_position, duration, seniority);
    }

    public static MenteeDto allParametersWithMenteeDto(String username, String email, String future_position, String duration, String seniority){
        return new MenteeDto(username, email,future_position, duration,seniority);
    }

    public static MenteeDto userPawelDto(){
        return allParametersWithMenteeDto("Pawel", "pawel@contact.pl", "Junior_Java_Developer", "ONE_MONTH", "INTERN");
    }

    public static MenteeDto userAdamDto(){
        return allParametersWithMenteeDto("Adam", "adam@contact.pl", "Junior_Java_Developer", "ONE_MONTH", "JUNIOR");
    }

    public static Mentee userPawelDao(){
        return allParametersWithMentee("00082Q", "Pawel", "pawel@contact.pl", "Junior_Java_Developer", Duration.ONE_MONTH, Seniority.INTERN);
    }
}
