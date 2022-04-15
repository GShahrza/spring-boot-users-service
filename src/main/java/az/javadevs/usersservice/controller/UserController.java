package az.javadevs.usersservice.controller;

import java.util.List;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import az.javadevs.usersservice.service.UserService;
import az.javadevs.usersservice.dto.request.UserRequestDTO;
import az.javadevs.usersservice.dto.response.UserResponseDTO;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/roles/add-to-user")
    public Boolean addRoleToUser(@RequestBody RoleToUserForm form) {
        return userService.addRoleToUser(form.getUsername(), form.getRoleName());
    }

    @DeleteMapping("/roles/remove-from-user")
    public Boolean removeRoleFromUser(@RequestBody RoleToUserForm form) {
        return userService.removeFromUser(form.getUsername(), form.getRoleName());
    }

    @GetMapping("/users/")
    public UserResponseDTO getUserByUsername(@RequestParam String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/users")
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public UserResponseDTO getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public Boolean updateUser(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO) {
        userRequestDTO.validate();
        return userService.updateUser(id, userRequestDTO);
    }
}

@Data
@AllArgsConstructor
class RoleToUserForm {
    private String username;
    private String roleName;
}

