package az.javadevs.usersservice.exceptions;

import java.util.NoSuchElementException;

public class UserNotFoundException extends NoSuchElementException {

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
