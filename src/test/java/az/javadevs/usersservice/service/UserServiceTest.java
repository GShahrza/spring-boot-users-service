package az.javadevs.usersservice.service;

import az.javadevs.usersservice.dao.entity.Account;
import az.javadevs.usersservice.dao.entity.Role;
import az.javadevs.usersservice.dao.entity.RoleEntity;
import az.javadevs.usersservice.dao.entity.UserEntity;
import az.javadevs.usersservice.dao.repository.AccountRepository;
import az.javadevs.usersservice.dao.repository.RoleRepository;
import az.javadevs.usersservice.dao.repository.UserRepository;
import az.javadevs.usersservice.dto.request.UserRequestDTO;
import az.javadevs.usersservice.dto.response.UserResponseDTO;
import az.javadevs.usersservice.exceptions.UserNotFoundException;
import az.javadevs.usersservice.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder encoder;

    UserService userService;

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

        userService = new UserServiceImpl(userRepository, accountRepository, roleRepository, encoder);
    }

    @Test
    public void getUserByIdTest() {
        when(userRepository.findById((long) 1)).thenReturn(Optional.ofNullable(user));
        assertEquals("gshahrza", userService.getUserById(1L).getUsername());
    }

    @Test
    public void getUserByUsernameTest() {
        when(accountRepository.findByUsername("gshahrza")).thenReturn(Optional.ofNullable(account));
        when(userRepository.findByAccountId(1L)).thenReturn(Optional.ofNullable(user));
        assertEquals(1L, userService.getUserByUsername("gshahrza").getId());
    }

    @Test
    public void getAllUsersTest(){
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));
        assertEquals(1,userService.getAllUsers().size());
        assertEquals("gshahrza@gmail.com",userService.getAllUsers().get(0).getEmail());
    }


}
