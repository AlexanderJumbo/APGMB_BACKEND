package com.lectura.apgmb.services.auth;

import com.lectura.apgmb.dtos.register.RegisterRequest;
import com.lectura.apgmb.dtos.user.ClientUpdateRequest;
import com.lectura.apgmb.dtos.user.UserResponse;
import com.lectura.apgmb.dtos.user.UserUpdateRequest;
import com.lectura.apgmb.dtos.user.UpdateResponse;
import com.lectura.apgmb.entities.secutiry.Role;
import com.lectura.apgmb.entities.secutiry.User;
import com.lectura.apgmb.exceptions.ObjectNotFoundException;
import com.lectura.apgmb.repositories.auth.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    /*@Autowired
    private JavaMailSender sender;*/
    @Override
    public Optional<User> findByUsername(String user) {
        return userRepository.findByUsername(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(Long idUser) {
        return userRepository.findById(idUser).stream().findFirst();
    }

    @Override
    public UserResponse getInfoUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User not found. User Id: " + userId));

        return mapDTO(user);
    }

    @Override
    public User registerUser(RegisterRequest registerRequest) {
        User newUser = new User();
        newUser.setName(registerRequest.getName());
        newUser.setLastName(registerRequest.getLastname());
        newUser.setUsername(registerRequest.getUserName());
        newUser.setDni(registerRequest.getDni());
        newUser.setEmail(registerRequest.getEmail());
        newUser.setAddress(registerRequest.getAddress());
        newUser.setNumberPhone(registerRequest.getNumberPhone());

        Role role = roleService.findByName(registerRequest.getRole())
                .orElseThrow(() -> new ObjectNotFoundException("Role not found."));
        newUser.setRole(role);
        if (registerRequest.getRole().equals("CLIENT")) {
            newUser.setPassword(null);
        } else {
            newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        }

        return userRepository.save(newUser);
    }

    @Override
    public UpdateResponse updateClient(ClientUpdateRequest updateRequest) {

        Optional<User> userFound = userRepository.findById(updateRequest.getUserId());
        if(userFound.isEmpty()) return getClientUpdateResponse(0L,"Usuario no existe en nuestra BD, por favor validar");

        userFound.get().setName(updateRequest.getName());
        userFound.get().setLastName(updateRequest.getLastname());
        userFound.get().setDni(updateRequest.getDni());
        userFound.get().setEmail(updateRequest.getEmail());
        userFound.get().setAddress(updateRequest.getAddress());
        userFound.get().setNumberPhone(updateRequest.getPhone());

        User userUpdate = userRepository.save(userFound.get());
        return getClientUpdateResponse(userUpdate.getId(), "Usuario actualizado correctamente");
    }

    @Override
    public UpdateResponse updateUser(UserUpdateRequest updateRequest) {
        User userFound = userRepository.findById(updateRequest.getUserId()).get();
        if(userFound.getId() == 0 || userFound.getId() == null) return getClientUpdateResponse(0L,"Usuario no existe en nuestra BD, por favor validar");

        userFound.setName(updateRequest.getName());
        userFound.setLastName(updateRequest.getLastname());
        userFound.setUsername(updateRequest.getUserName());
        userFound.setDni(updateRequest.getDni());
        userFound.setEmail(updateRequest.getEmail());
        userFound.setAddress(updateRequest.getAddress());
        userFound.setNumberPhone(updateRequest.getNumberPhone());

        Role role = roleService.findByName(updateRequest.getRole()).get();
        if(role.getId() == 0 || role.getId() == null) return getClientUpdateResponse(0L, "Role escogido no existe");
        userFound.setRole(role);
        if(!updateRequest.getPassword().isEmpty()){
            userFound.setPassword(passwordEncoder.encode(updateRequest.getPassword()));
        }

        User userUpdate = userRepository.save(userFound);
        return getClientUpdateResponse(userUpdate.getId(), "Usuario actualizado correctamente");
    }

    @Override
    public List<UserResponse> getAllUsers() {

        return userRepository.findAll().stream().filter(u -> !u.getRole().getName().equals("CLIENT") && u.isActive()).map(u -> new UserResponse(
                u.getId(),
                u.getName(),
                u.getLastName(),
                u.getUsername(),
                u.getAddress(),
                u.getEmail(),
                u.getDni(),
                u.getNumberPhone(),
                u.getProfilePhoto(),
                u.getCreateAt(),
                u.getRole().getName()
        )).collect(Collectors.toList());
    }

    @Override
    public boolean deleteUser(Long idUser) {
        Optional<User> userFound = userRepository.findById(idUser);
        if(userFound.isEmpty()) return false;
        userFound.get().setActive(!userFound.get().isActive());
        userRepository.save(userFound.get());
        return true;
    }

    private static UpdateResponse getClientUpdateResponse(Long userId, String message) {
        UpdateResponse response = new UpdateResponse();
        response.setIdUser(userId);
        response.setMessage(message);
        return response;
    }

    private UserResponse mapDTO(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setName(user.getName());
        userResponse.setUserName(user.getUsername());
        userResponse.setDni(user.getDni());
        userResponse.setAddress(user.getAddress());
        userResponse.setNumberPhone(user.getNumberPhone());

        return userResponse;
    }
}
