package pl.reskilled.menteeManagerMicroservices.project.exception.response;

public interface SampleNameExistsException {

    default NameExistsException sampleNameExistsException(String name) {
        return new NameExistsException(name);
    }
}
