package az.javadevs.usersservice.service.impl;

import az.javadevs.usersservice.dao.entity.Role;
import az.javadevs.usersservice.dao.entity.Account;
import az.javadevs.usersservice.dao.entity.UserEntity;
import az.javadevs.usersservice.dao.entity.RoleEntity;
import az.javadevs.usersservice.dao.repository.UserRepository;
import az.javadevs.usersservice.dao.repository.RoleRepository;
import az.javadevs.usersservice.dao.repository.AccountRepository;

import az.javadevs.usersservice.dto.request.UserRequestDTO;
import az.javadevs.usersservice.dto.response.UserResponseDTO;

import az.javadevs.usersservice.exceptions.RoleNotFoundException;
import az.javadevs.usersservice.exceptions.UserNotFoundException;
import az.javadevs.usersservice.exceptions.DuplicateRoleException;
import az.javadevs.usersservice.exceptions.DuplicateUsernameException;
import az.javadevs.usersservice.service.UserService;


import java.util.List;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

    final PasswordEncoder encoder;

    final RoleRepository roleRepository;

    final UserRepository userRepository;

    final AccountRepository accountRepository;

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        return new UserResponseDTO(userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User not found")));
    }

    @Override
    public Boolean addUser(UserRequestDTO userRequestDTO) {
        userRequestDTO.setPassword(encoder.encode(userRequestDTO.getPassword()));
        UserEntity user = new UserEntity(userRequestDTO);
        try {
            getAccount(user.getAccount().getUsername());
            throw new DuplicateUsernameException(String.format("Username: %s is already exists",
                    user.getAccount().getUsername()));
        } catch (UserNotFoundException ex) {
            user.getAccount().getRoles().add(RoleEntity
                    .builder()
                    .id(3L)
                    .roleName(Role.ROLE_USER)
                    .build());
        }

        try {
            accountRepository.save(user.getAccount());
            userRepository.save(user);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException("An error occurred, please try again");
        }
        return true;
    }

    @Override
    public UserResponseDTO getUserByUsername(String username) {
        try {
            Account account = accountRepository.findByUsername(username).orElseThrow();
            return new UserResponseDTO(userRepository.findByAccountId(account.getId()).orElseThrow());
        } catch (UserNotFoundException ex) {
            throw new UserNotFoundException(String.format("Username: %s not found", username));
        }
    }

    @Override
    public Boolean addRoleToUser(String username, String roleName) {
        Account account = getAccount(username);
        RoleEntity role = getRole(roleName);
        if (account.getRoles().add(role)) {
            accountRepository.save(account);
            return true;
        }
        throw new DuplicateRoleException("The role is already there!");
    }

    @Override
    public Boolean removeFromUser(String username, String roleName) {
        Account account = getAccount(username);
        RoleEntity role = getRole(roleName);
        if (account.getRoles().remove(role)) {
            accountRepository.save(account);
            return true;
        }
        throw new RoleNotFoundException("The user does not have this role");
    }

    @Override
    public Boolean updateUser(Long id, UserRequestDTO userRequestDTO) {
        try {
            getUserByIdAndByUsername(id, userRequestDTO.getUsername());
            throw new DuplicateUsernameException(String.format("Username: %s is already exists",
                    userRequestDTO.getUsername()));
        } catch (UserNotFoundException ex) {
            try {
                userRepository.save(new UserEntity(userRequestDTO));
            } catch (Exception exception) {
                throw new RuntimeException("An error occurred, please try again");
            }
        }
        return true;
    }

    private RoleEntity getRole(String roleName) {
        return roleRepository.findByRoleName(Role.valueOf(roleName))
                .orElseThrow(() -> new RoleNotFoundException(String.format("Role - %s not found", roleName)));
    }

    private Account getAccount(String username) {
        return accountRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(String.format("Username: %s not found", username)));
    }

    private Account getUserByIdAndByUsername(Long id, String username) {
        return accountRepository.findByIdAndUsername(id, username)
                .orElseThrow(() -> new UserNotFoundException(String.format("Username: %s not found", username)));
    }
}
