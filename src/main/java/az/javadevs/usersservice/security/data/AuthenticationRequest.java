package az.javadevs.usersservice.security.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class AuthenticationRequest {
    private final String username;
    private final String password;
}
