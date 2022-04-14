package az.javadevs.usersservice.exceptions;

import org.springframework.dao.DuplicateKeyException;

public class DuplicateRoleException extends DuplicateKeyException {
    public DuplicateRoleException(String msg) {
        super(msg);
    }

    public DuplicateRoleException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
