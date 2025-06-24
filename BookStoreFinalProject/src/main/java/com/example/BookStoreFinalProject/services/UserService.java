package com.example.BookStoreFinalProject.services;

import com.example.BookStoreFinalProject.dtos.LoginRequest;
import com.example.BookStoreFinalProject.dtos.RegistrationRequest;
import com.example.BookStoreFinalProject.dtos.Response;
import com.example.BookStoreFinalProject.dtos.UserDTO;
import com.example.BookStoreFinalProject.entities.User;

public interface UserService {

    Response registerUser(RegistrationRequest registrationRequest);

    Response loginUser(LoginRequest loginRequest);

    Response getAllUsers();

    User getCurrentLoggedInUser();

    Response updateUserDetails(UserDTO userDTO);

    Response deleteOwnAccount();

    Response getUserOrderHistory();

    Response getUserProfile();

    Response logoutUser();
}
