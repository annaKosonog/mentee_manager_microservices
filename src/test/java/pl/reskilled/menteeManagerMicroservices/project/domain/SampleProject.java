package pl.reskilled.menteeManagerMicroservices.project.domain;

import static pl.reskilled.menteeManagerMicroservices.project.domain.ProjectUtil.allParametersForProject;
import static pl.reskilled.menteeManagerMicroservices.project.domain.ProjectUtil.allParametersWithoutId;
import static pl.reskilled.menteeManagerMicroservices.project.domain.ProjectUtil.developerSet;
import static pl.reskilled.menteeManagerMicroservices.project.domain.ProjectUtil.techStackSet;

public interface SampleProject  {

    default Project firstProjectWithoutId() {
        return allParametersWithoutId("Secret keys", developerSet(), techStackSet(), "User write application");
    }


    default Project secretKey() {
        return allParametersForProject("61e98e0b825ad234e3725cca", "Secret keys", developerSet(), techStackSet(), "User second");
    }

    default Project pacman(){
        return allParametersForProject("24ee32b6-6b15-11eb-9439-0242ac130002",
                "Pacman Game",
                developerSet(),
                techStackSet(),
                "RPG game"
                );
    }


}
