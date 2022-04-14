package az.javadevs.usersservice.dto.request;

import az.javadevs.usersservice.exceptions.FieldNullPointerException;
import az.javadevs.usersservice.exceptions.WrongFieldException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequestDTO {

    String firstName;
    String lastName;
    String username;
    String email;
    String password;

    public void validate() {
        if (this.firstName == null || firstName.trim().isEmpty()) {
            throw new FieldNullPointerException("Field cannot be null: first name");
        }
        if (this.lastName == null || lastName.trim().isEmpty()) {
            throw new FieldNullPointerException("Field cannot be null: last name");
        }
        if (this.username == null || username.trim().isEmpty()) {
            throw new FieldNullPointerException("Field cannot be null: username");
        }
        if (this.email == null || email.trim().isEmpty()) {
            throw new FieldNullPointerException("Field cannot be null: email");
        }
        if (this.password == null || password.trim().isEmpty()) {
            throw new FieldNullPointerException("Field cannot be null: password");
        }
        if (!(this.password.matches("^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$*%^&+=])"
                + "(?=\\S+$).{8,20}$"))) {
            throw new WrongFieldException("Password:\nIt contains at least 8 characters and at most 20 characters.\n" +
                    "It contains at least one digit.\n" +
                    "It contains at least one upper case alphabet.\n" +
                    "It contains at least one lower case alphabet.\n" +
                    "It contains at least one special character which includes !@#$%&*()-+=^.\n" +
                    "It doesn’t contain any white space.");
        }
        if (!this.username.matches("^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$")) {
            throw new WrongFieldException("It contains at least 8 characters and at most 20 characters.\n" +
                    "It contains at least one digit.\n" +
                    "It doesn’t contain any white space." +
                    "no _ or . at the beginning" +
                    "no _ or . at the end" +
                    "no __ or _. or ._ or .. inside");
        }
        if (!this.email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:" +
                "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])" +
                "*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|" +
                "[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|" +
                "[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b" +
                "\\x0c\\x0e-\\x7f])+)])")) {
            throw new WrongFieldException("wrong email!");
        }
    }
}
