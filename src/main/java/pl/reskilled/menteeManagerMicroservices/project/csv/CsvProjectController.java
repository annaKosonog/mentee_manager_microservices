package pl.reskilled.menteeManagerMicroservices.project.csv;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CsvProjectController {

    private final CsvView csvView;


    @GetMapping("/csv/project")
    public void returningCsvContentFromAnApiProject(HttpServletResponse response, @RequestParam("file") File fileName) throws Exception {

        csvView.prepareResponse(response, fileName);
        csvView.buildCsvDocumentProject(response);

    }

    @GetMapping("/csv/mentees")
    public void returningCsvContentFromAnApiMentees(HttpServletResponse response, @RequestParam("file") File fileName) throws IOException {

        csvView.prepareResponse(response, fileName);
        csvView.buildCsvDocumentMentee(response);
    }

    @GetMapping("/csv/teams")
    public void returningCsvContentFromAnApiTeams(HttpServletResponse response, @RequestParam("file") File fileName) throws IOException {

        csvView.prepareResponse(response, fileName);
        csvView.buildCsvDocumentTeams(response);
    }
}
