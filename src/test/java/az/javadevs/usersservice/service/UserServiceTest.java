package az.javadevs.usersservice.service;

import az.javadevs.usersservice.dao.entity.Account;
import az.javadevs.usersservice.dao.entity.Role;
import az.javadevs.usersservice.dao.entity.RoleEntity;
import az.javadevs.usersservice.dao.entity.UserEntity;
import az.javadevs.usersservice.dao.repository.AccountRepository;
import az.javadevs.usersservice.dao.repository.RoleRepository;
import az.javadevs.usersservice.dao.repository.UserRepository;
import az.javadevs.usersservice.exceptions.RoleNotFoundException;
import az.javadevs.usersservice.exceptions.UserNotFoundException;
import az.javadevs.usersservice.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private UserServiceImpl userService;

    RoleEntity role;

    RoleEntity roleUser;

    Account account;

    UserEntity user;

    @BeforeEach
    void setMockOutput() {
        Set<RoleEntity> roles = new HashSet<>();

        role = RoleEntity.builder()
                .id(1L)
                .roleName(Role.ROLE_ADMIN)
                .build();
        roles.add(role);
        roleUser = RoleEntity.builder()
                .id(3L)
                .roleName(Role.ROLE_USER)
                .build();
        account = Account.builder()
                .id(1L)
                .username("gshahrza")
                .password("qweR1234*")
                .email("gshahrza@gmail.com")
                .roles(roles)
                .build();
        user = UserEntity.builder()
                .id(1L)
                .firstName("Shahrza")
                .lastName("Gahramanov")
                .account(account)
                .build();
    }

    @Test
    public void getUserByIdTest() {
        when(userRepository.findById((long) 1)).thenReturn(Optional.ofNullable(user));
        assertEquals("gshahrza", userService.getUserById(1L).getUsername());
    }

    @Test
    public void getAllUsersTest() {
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));
        assertEquals(1, userService.getAllUsers().size());
        assertEquals("gshahrza@gmail.com", userService.getAllUsers().get(0).getEmail());
    }

    @Test
    public void addRoleToUserTest() {
        when(accountRepository.findByUsername("gshahrza")).thenReturn(Optional.ofNullable(account));
        when(roleRepository.findByRoleName(Role.ROLE_USER)).thenReturn(Optional.ofNullable(roleUser));
        when(roleRepository.findByRoleName(Role.ROLE_ADMIN)).thenReturn(Optional.ofNullable(role));
        when(accountRepository.save(account)).thenReturn(account);

        assertAll(
                () -> assertTrue(userService.addRoleToUser("gshahrza", "ROLE_USER")),
                () -> assertThrows(IllegalArgumentException.class, () -> userService.addRoleToUser("gshahrza",
                        "ROLE_OTHER")),
                () -> assertThrows(UserNotFoundException.class, () -> userService.addRoleToUser("ex",
                        "ROLE_USER")),
                () -> assertThrows(DuplicateKeyException.class, () -> userService.addRoleToUser("gshahrza",
                        "ROLE_ADMIN"))
        );
    }

    @Test
    public void removeFromUserTest() {
        when(accountRepository.findByUsername("gshahrza")).thenReturn(Optional.ofNullable(account));
        when(roleRepository.findByRoleName(Role.ROLE_USER)).thenReturn(Optional.ofNullable(roleUser));
        when(roleRepository.findByRoleName(Role.ROLE_ADMIN)).thenReturn(Optional.ofNullable(role));
        when(accountRepository.save(account)).thenReturn(account);

        assertAll(
                () -> assertThrows(RoleNotFoundException.class, () -> userService.removeFromUser("gshahrza", "ROLE_USER")),
                () -> assertThrows(IllegalArgumentException.class, () -> userService.removeFromUser("gshahrza",
                        "ROLE_OTHER")),
                () -> assertThrows(UserNotFoundException.class, () -> userService.removeFromUser("ex",
                        "ROLE_USER")),
                () -> assertTrue(userService.removeFromUser("gshahrza",
                        "ROLE_ADMIN"))
        );
    }

    @Test
    public void getUserByUsernameTest() {
        when(accountRepository.findByUsername("gshahrza")).thenReturn(Optional.ofNullable(account));
        when(userRepository.findByAccountId(1L)).thenReturn(Optional.ofNullable(user));
        assertEquals(1L, userService.getUserByUsername("gshahrza").getId());
    }
}
