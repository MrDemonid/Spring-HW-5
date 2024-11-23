package mr.demonid.spring.hw5.exceptions;

import lombok.Getter;

@Getter
public abstract class ApplicationBaseException extends RuntimeException {
    protected String head;

    public ApplicationBaseException(String head, String body) {
        super(body);
        this.head = head;
    }
}
