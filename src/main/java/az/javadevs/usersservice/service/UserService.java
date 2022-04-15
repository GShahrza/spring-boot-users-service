package az.javadevs.usersservice.service;

import az.javadevs.usersservice.dao.entity.RoleEntity;
import az.javadevs.usersservice.dto.request.UserRequestDTO;
import az.javadevs.usersservice.dto.response.UserResponseDTO;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> getAllUsers();

    UserResponseDTO getUserById(Long id);

    Boolean addUser(UserRequestDTO user);

    UserResponseDTO getUserByUsername(String username);

    Boolean addRoleToUser(String username, String roleName);

    Boolean removeFromUser(String username, String roleName);

    Boolean updateUser(Long id, UserRequestDTO userRequestDTO);
}
