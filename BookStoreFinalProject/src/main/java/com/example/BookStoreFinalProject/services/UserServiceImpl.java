package com.example.BookStoreFinalProject.services;

import com.example.BookStoreFinalProject.dtos.*;
import com.example.BookStoreFinalProject.entities.User;
import com.example.BookStoreFinalProject.enums.UserRole;
import com.example.BookStoreFinalProject.exceptions.InvalidLoginException;
import com.example.BookStoreFinalProject.exceptions.ResourceNotFoundException;
import com.example.BookStoreFinalProject.repositories.UserRepository;
import com.example.BookStoreFinalProject.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final ModelMapper modelMapper;

    @Override
    public Response registerUser(RegistrationRequest request) {
        log.info("Registering user: {}", request.getEmail());

        UserRole finalRole = request.getRole() != null ? request.getRole() : UserRole.CUSTOMER;

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .role(finalRole)
                .active(true)
                .build();

        userRepository.save(user);

        return Response.builder()
                .status(200)
                .message("User successfully registered.")
                .build();
    }

    @Override
    public Response loginUser(LoginRequest loginRequest) {
        User foundUser = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new InvalidLoginException("Email not found"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), foundUser.getPassword())) {
            throw new InvalidLoginException("Invalid password");
        }

        String jwt = jwtUtils.createToken(foundUser.getEmail());

        return Response.builder()
                .status(200)
                .message("Login successful.")
                .token(jwt)
                .role(foundUser.getRole())
                .active(foundUser.isActive())
                .expirationTime("6 months")
                .build();
    }

    @Override
    public Response getAllUsers() {
        log.info("Retrieving all users");

        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        List<UserDTO> userDTOs = modelMapper.map(users, new TypeToken<List<UserDTO>>() {}.getType());

        return Response.builder()
                .status(200)
                .message("Fetched all users.")
                .users(userDTOs)
                .build();
    }

    @Override
    public User getCurrentLoggedInUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found in system"));
    }

    @Override
    public Response updateUserDetails(UserDTO userDTO) {
        User user = getCurrentLoggedInUser();

        if (userDTO.getEmail() != null) user.setEmail(userDTO.getEmail());
        if (userDTO.getFirstName() != null) user.setFirstName(userDTO.getFirstName());
        if (userDTO.getLastName() != null) user.setLastName(userDTO.getLastName());
        if (userDTO.getPhoneNumber() != null) user.setPhoneNumber(userDTO.getPhoneNumber());

        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        userRepository.save(user);

        return Response.builder()
                .status(200)
                .message("User updated.")
                .build();
    }

    @Override
    public Response deleteOwnAccount() {
        User user = getCurrentLoggedInUser();

        userRepository.delete(user);

        return Response.builder()
                .status(200)
                .message("Account removed.")
                .build();
    }

    @Override
    public Response getUserOrderHistory() {
        return Response.builder()
                .status(200)
                .message("Order history placeholder.")
                .build();
    }

    @Override
    public Response getUserProfile() {
        User user = getCurrentLoggedInUser();
        UserDTO dto = modelMapper.map(user, UserDTO.class);

        return Response.builder()
                .status(200)
                .message("User profile retrieved.")
                .user(dto)
                .build();
    }

    @Override
    public Response logoutUser() {
        return Response.builder()
                .status(200)
                .message("Logged out.")
                .build();
    }
}
