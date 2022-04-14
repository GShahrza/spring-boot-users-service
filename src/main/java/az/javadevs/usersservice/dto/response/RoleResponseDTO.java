package az.javadevs.usersservice.dto.response;

import az.javadevs.usersservice.dao.entity.Role;
import az.javadevs.usersservice.dao.entity.RoleEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleResponseDTO {

    Long id;
    Role roleName;

    public RoleResponseDTO(RoleEntity role) {
        this.id = role.getId();
        this.roleName = role.getRoleName();
    }
}
