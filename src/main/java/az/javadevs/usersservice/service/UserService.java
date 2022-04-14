package az.javadevs.usersservice.service;

import az.javadevs.usersservice.dao.entity.RoleEntity;
import az.javadevs.usersservice.dto.request.UserRequestDTO;
import az.javadevs.usersservice.dto.response.UserResponseDTO;

import java.util.List;

public interface UserService {

    Boolean addUser(UserRequestDTO user);

    Boolean updateUser(Long id,UserRequestDTO userRequestDTO);

    Boolean saveRole(RoleEntity role);

    Boolean addRoleToUser(String username, String roleName);

    UserResponseDTO getUserById(Long id);

    UserResponseDTO getUserByUsername(String username);

    List<UserResponseDTO> getAllUsers();

    Boolean removeFromUser(String username, String roleName);
}
