package com.lectura.apgmb.services.auth;

import com.lectura.apgmb.dtos.register.RegisterRequest;
import com.lectura.apgmb.dtos.user.ClientUpdateRequest;
import com.lectura.apgmb.dtos.user.UserResponse;
import com.lectura.apgmb.dtos.user.UserUpdateRequest;
import com.lectura.apgmb.dtos.user.UpdateResponse;
import com.lectura.apgmb.entities.secutiry.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String user);
    Optional<User> findByEmail(String email);

    Optional<User> findById(Long idUser);

    UserResponse getInfoUser(Long userId);

    User registerUser(RegisterRequest registerRequest);
    UpdateResponse updateClient(ClientUpdateRequest updateRequest);
    UpdateResponse updateUser(UserUpdateRequest updateRequest);

    List<UserResponse> getAllUsers();
    boolean deleteUser(Long idUser);
    //UpdateProfileResponse updateProfile(UpdateProfileRequest updateProfileRequest, Long id);

    //Boolean sendEmail(MailRequest mailRequest);
}
