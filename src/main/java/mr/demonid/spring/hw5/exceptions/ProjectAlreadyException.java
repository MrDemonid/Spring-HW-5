package mr.demonid.spring.hw5.exceptions;

public class ProjectAlreadyException extends ApplicationBaseException {

    public ProjectAlreadyException(String head, String body) {
        super(head, body);
    }
}
