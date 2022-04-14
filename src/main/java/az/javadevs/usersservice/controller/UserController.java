package az.javadevs.usersservice.controller;

import az.javadevs.usersservice.dto.request.UserRequestDTO;
import az.javadevs.usersservice.dto.response.UserResponseDTO;
import az.javadevs.usersservice.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<UserResponseDTO> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public UserResponseDTO getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/users/")
    public UserResponseDTO getUserByUsername(@RequestParam String username) {
        return userService.getUserByUsername(username);
    }

    @PutMapping("/users/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO) {
        userRequestDTO.validate();
        userService.updateUser(id, userRequestDTO);
    }

    @PostMapping("/roles/add-to-user")
    public Boolean addRoleToUser(@RequestBody RoleToUserForm form) {
        return userService.addRoleToUser(form.getUsername(), form.getRoleName());
    }

    @DeleteMapping("/roles/remove-from-user")
    public ResponseEntity.BodyBuilder removeFromUser(@RequestBody RoleToUserForm form) {
        userService.removeFromUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok();
    }
}

@Data
class RoleToUserForm {
    private String username;
    private String roleName;
}

