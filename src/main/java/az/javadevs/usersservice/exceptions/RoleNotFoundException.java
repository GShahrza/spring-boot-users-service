package az.javadevs.usersservice.exceptions;

public class RoleNotFoundException extends IllegalArgumentException{
    public RoleNotFoundException() {
    }

    public RoleNotFoundException(String message) {
        super(message);
    }
}
