package com.lectura.apgmb.controllers;

import com.lectura.apgmb.dtos.register.RegisterRequest;
import com.lectura.apgmb.dtos.register.RegisterResponse;
import com.lectura.apgmb.dtos.user.UpdateResponse;
import com.lectura.apgmb.dtos.user.UserResponse;
import com.lectura.apgmb.dtos.user.UserUpdateRequest;
import com.lectura.apgmb.services.auth.AuthenticationService;
import com.lectura.apgmb.services.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private PasswordEncoder p;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getInfoUser(@PathVariable Long userId){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getInfoUser(userId));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerUser(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.registerUser(registerRequest));
    }

    @PutMapping("/update")
    public ResponseEntity<UpdateResponse> updateUser(@RequestBody UserUpdateRequest updateRequest){
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(updateRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @PutMapping("/delete/{userId}")
    public ResponseEntity<Boolean> updateUser(@PathVariable Long userId){
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(userId));
    }
}
