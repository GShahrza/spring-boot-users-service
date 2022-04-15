package az.javadevs.usersservice.controller;

import java.util.List;
import java.util.Collections;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;

import az.javadevs.usersservice.dao.entity.Role;
import az.javadevs.usersservice.dao.entity.Account;
import az.javadevs.usersservice.service.UserService;
import az.javadevs.usersservice.dao.entity.UserEntity;
import az.javadevs.usersservice.dao.entity.RoleEntity;
import az.javadevs.usersservice.dto.request.UserRequestDTO;
import az.javadevs.usersservice.dto.response.UserResponseDTO;
import az.javadevs.usersservice.exceptions.WrongFieldException;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    RoleEntity role;

    Account account;

    UserEntity user;

    @BeforeEach
    void setMockOutput() {
        role = RoleEntity.builder()
                .id(3L)
                .roleName(Role.ROLE_USER)
                .build();
        account = Account.builder()
                .id(1L)
                .username("gshahrza")
                .password("qweR1234*")
                .email("gshahrza@gmail.com")
                .roles(Collections.singleton(role))
                .build();
        user = UserEntity.builder()
                .id(1L)
                .firstName("Shahrza")
                .lastName("Gahramanov")
                .account(account)
                .build();

    }

    @DisplayName("All user: ")
    @Test
    public void getAllUsers() {
        when(userService.getAllUsers()).thenReturn(List.of(new UserResponseDTO(user)));

        assertAll(
                () -> assertEquals(new UserResponseDTO(user), userController.getAllUsers().get(0)),
                () -> assertEquals(1, userController.getAllUsers().size())
        );
    }

    @DisplayName("User by Id: ")
    @Test
    public void getUserByIdTest() {
        when(userService.getUserById(1L)).thenReturn(new UserResponseDTO(user));
        when(userService.getUserById(2L)).thenReturn(null);

        assertAll(
                () -> assertEquals("gshahrza", userController.getUserById(1L).getUsername()),
                () -> assertNotNull(userController.getUserById(1L)),
                () -> assertNull(userController.getUserById(2L))
        );
    }

    @DisplayName("User by username: ")
    @Test
    public void getUserByUsernameTest() {
        when(userService.getUserByUsername("gshahrza")).thenReturn(new UserResponseDTO(user));

        assertAll(
                () -> assertEquals("gshahrza", userController.getUserByUsername("gshahrza").getUsername()),
                () -> assertNotNull(userController.getUserByUsername("gshahrza")),
                () -> assertNull(userController.getUserByUsername("example"))
        );
    }

    @DisplayName("User update: ")
    @Test
    public void updateUserTest() {
        UserRequestDTO userRequest = UserRequestDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getAccount().getEmail())
                .username(user.getAccount().getUsername())
                .password(user.getAccount().getPassword())
                .build();
        when(userService.updateUser(1L, userRequest)).thenReturn(true);
        assertTrue(userController.updateUser(1L, userRequest));

        userRequest.setPassword("1234");
        assertThrows(WrongFieldException.class, () -> userController.updateUser(1L, userRequest));

    }

    @DisplayName("Add role to User: ")
    @Test
    public void addRoleToUserTest() {
        RoleToUserForm roleToUserForm = new RoleToUserForm("gshahrza", "ROLE_USER");

        when(userService.addRoleToUser("gshahrza", "ROLE_USER")).thenReturn(true);
        assertTrue(userController.addRoleToUser(roleToUserForm));

        roleToUserForm = new RoleToUserForm("gshahrza", "ROLE_ADMIN");

        when(userService.addRoleToUser("gshahrza", "ROLE_ADMIN")).thenReturn(false);
        assertFalse(userController.addRoleToUser(roleToUserForm));
    }

    @DisplayName("Remove role from User: ")
    @Test
    public void removeRoleFromUserTest() {
        RoleToUserForm roleToUserForm = new RoleToUserForm("gshahrza", "ROLE_USER");

        when(userService.removeFromUser("gshahrza", "ROLE_USER")).thenReturn(true);
        assertTrue(userController.removeRoleFromUser(roleToUserForm));

        roleToUserForm = new RoleToUserForm("gshahrza", "ROLE_MODERATOR");

        when(userService.removeFromUser("gshahrza", "ROLE_MODERATOR")).thenReturn(false);
        assertFalse(userController.removeRoleFromUser(roleToUserForm));
    }
}
