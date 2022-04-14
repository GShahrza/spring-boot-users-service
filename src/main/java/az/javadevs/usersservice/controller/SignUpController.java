package az.javadevs.usersservice.controller;

import az.javadevs.usersservice.dto.request.UserRequestDTO;
import az.javadevs.usersservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignUpController {

    private final UserService userService;

    @PostMapping("/signup")
    public Boolean add(@RequestBody UserRequestDTO userRequestDTO) {
        userRequestDTO.validate();
        return userService.addUser(userRequestDTO);
    }
}
