package az.javadevs.usersservice.controller;

import az.javadevs.usersservice.service.UserService;
import az.javadevs.usersservice.dto.request.UserRequestDTO;
import az.javadevs.usersservice.exceptions.WrongFieldException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SignUpControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private SignUpController signUpController;

    @Test
    public void testAdd(){
        UserRequestDTO userRequest = new UserRequestDTO("Shahrza","Gahramanov","gshahrza",
                "gshahrza@gmail.com","qweR1234*");

        UserRequestDTO userRequestTwo = new UserRequestDTO("Seymur","Mikayilov","smikayilov08",
                "smikayilov08@gmail.com","qweR1234");

        when(userService.addUser(userRequest)).thenReturn(true);

        assertAll(
                () -> assertTrue(signUpController.add(userRequest)),
                () -> assertThrows(WrongFieldException.class,() -> signUpController.add(userRequestTwo)),
                () ->  assertThrows(NullPointerException.class,() -> signUpController.add(null))
        );
    }
}
