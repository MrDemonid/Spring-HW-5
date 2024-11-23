package mr.demonid.spring.hw5.exceptions;

public class ElementNotFoundException extends ApplicationBaseException {

    public ElementNotFoundException(String head, String body) {
        super(head, body);
    }
}
