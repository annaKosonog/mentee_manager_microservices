package pl.reskilled.menteeManagerMicroservices.mentee;

import pl.reskilled.menteeManagerMicroservices.mentee.domain.dao.Duration;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.dao.Mentee;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.dao.Seniority;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.dto.MenteeDto;

public class MenteeUtil {

    public static Mentee allParametersWithMentee(String id, String username, String email, String futurePosition, Duration duration, Seniority seniority) {
        return new Mentee(id, username, email, futurePosition, duration, seniority);
    }

    public static Mentee allParametersWithoutIdWithMentee(String username, String email, String futurePosition, Duration duration, Seniority seniority){
        return new Mentee(null, username, email, futurePosition, duration, seniority);
    }

    public static MenteeDto allParameterMenteeDto(String username, String email, String futurePosition, String duration, String seniority){
        return new MenteeDto(username, email, futurePosition, duration, seniority);
    }

    public static MenteeDto menteeAdam(){
        return allParameterMenteeDto("Adam", "mentoring@contact.pl", "Junior Java Developer", "SIX_MONTH", "INTERN");
    }



}
