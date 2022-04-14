package az.javadevs.usersservice.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.SignatureException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ErrorResponse handleUsernameNotFoundException(UserNotFoundException ex) {
        return response(ex, HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler
    public ErrorResponse handleRoleNotFoundException(RoleNotFoundException ex) {
        return response(ex, HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler
    public ErrorResponse handleDuplicateRoleException(DuplicateRoleException ex) {
        return response(ex, HttpStatus.CONFLICT.value());
    }

    @ExceptionHandler
    public ErrorResponse handleDuplicateUsernameException(DuplicateUsernameException ex) {
        return response(ex, HttpStatus.CONFLICT.value());
    }

    @ExceptionHandler
    public ErrorResponse handleFieldNullPointerException(FieldNullPointerException ex) {
        return response(ex, HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler
    ErrorResponse handleWrongPasswordException(WrongFieldException ex) {
        return response(ex, HttpStatus.BAD_REQUEST.value());
    }

    private <T extends RuntimeException> ErrorResponse response(T exception, int statusCode) {
        return new ErrorResponse(exception.getMessage(), statusCode, LocalDateTime.now());
    }

    @ExceptionHandler(SignatureException.class)
    public ErrorResponse signature() {
        return new ErrorResponse("Token is invalid", HttpStatus.UNAUTHORIZED.value(), LocalDateTime.now());
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ErrorResponse time() {
        return new ErrorResponse("Token is expired", HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
    }
}
