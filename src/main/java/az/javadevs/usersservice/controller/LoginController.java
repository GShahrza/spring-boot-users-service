package az.javadevs.usersservice.controller;

import az.javadevs.usersservice.exceptions.UserNotFoundException;
import az.javadevs.usersservice.security.MyUserDetailsService;
import az.javadevs.usersservice.security.data.AuthenticationRequest;
import az.javadevs.usersservice.security.data.AuthenticationResponse;
import az.javadevs.usersservice.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager manager;

    private final MyUserDetailsService userDetailsService;

    private final JwtUtil jwtUtil;

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        } catch (AuthenticationException e) {
            throw new UserNotFoundException("User Not Found!");
        }
        final String jwt = jwtUtil.generateToken(userDetailsService.loadUserByUsername(request.getUsername()));
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}

