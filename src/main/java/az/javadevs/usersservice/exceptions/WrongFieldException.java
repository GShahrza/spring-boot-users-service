package az.javadevs.usersservice.exceptions;

public class WrongFieldException extends RuntimeException{

    public WrongFieldException() {
    }

    public WrongFieldException(String message) {
        super(message);
    }
}
