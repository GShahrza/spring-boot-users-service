package az.javadevs.usersservice.dto.response;

import az.javadevs.usersservice.dao.entity.UserEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponseDTO {

    Long id;
    String firstName;
    String lastName;
    String username;
    String email;
    String password;
    Set<RoleResponseDTO> roles;

    public UserResponseDTO(UserEntity user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.username = user.getAccount().getUsername();
        this.email = user.getAccount().getEmail();
        this.password = user.getAccount().getPassword();
        this.roles = user.getAccount().getRoles().stream()
                .map(RoleResponseDTO::new)
                .collect(Collectors.toSet());
    }

}
