package az.javadevs.usersservice.controller;

import az.javadevs.usersservice.dao.entity.Account;
import az.javadevs.usersservice.dao.entity.Role;
import az.javadevs.usersservice.dao.entity.RoleEntity;
import az.javadevs.usersservice.security.MyUserDetails;
import az.javadevs.usersservice.security.MyUserDetailsService;
import az.javadevs.usersservice.security.data.AuthenticationRequest;
import az.javadevs.usersservice.security.data.AuthenticationResponse;
import az.javadevs.usersservice.security.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

    @Mock
    private AuthenticationManager manager;

    @Mock
    private MyUserDetailsService userDetailsService;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    LoginController loginController;

    RoleEntity role;

    Account account;

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
    }

    @Test
    public void testLogin() {
        UserDetails userDetails = new MyUserDetails(account);
        AuthenticationRequest request = new AuthenticationRequest("gshahrza", "qweR1234*");

        when(manager.authenticate(new UsernamePasswordAuthenticationToken("gshahrza", "qweR1234*")))
                .thenReturn(new UsernamePasswordAuthenticationToken("gshahrza", "qweR1234*"));
        when(userDetailsService.loadUserByUsername("gshahrza")).thenReturn(userDetails);
        when(jwtUtil.generateToken(userDetails)).thenReturn("jwtBody");

        ResponseEntity<?> response = loginController.login(request);
        AuthenticationResponse authenticationResponse = (AuthenticationResponse) response.getBody();

        assertNotNull(response.getBody());
        assertEquals("jwtBody", authenticationResponse.getJwt());
    }
}
