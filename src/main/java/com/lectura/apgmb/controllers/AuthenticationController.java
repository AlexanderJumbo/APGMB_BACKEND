package com.lectura.apgmb.controllers;

import com.lectura.apgmb.dtos.auth.AuthenticationRequest;
import com.lectura.apgmb.dtos.auth.AuthenticationResponse;
import com.lectura.apgmb.services.auth.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    //@CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.authenticate(authenticationRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request){
        authenticationService.logout(request);
        return ResponseEntity.status(HttpStatus.OK).body("Logout successfully");
    }

    @GetMapping("/validate-token")
    public ResponseEntity<Boolean> validate(@RequestParam String jwt){
        boolean isTokenValid = authenticationService.validateToken(jwt);
        System.out.println(isTokenValid);
        return ResponseEntity.ok(isTokenValid);
    }
}
