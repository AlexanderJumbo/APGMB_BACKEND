package com.lectura.apgmb.services.auth;

import com.lectura.apgmb.dtos.auth.AuthenticationRequest;
import com.lectura.apgmb.dtos.auth.AuthenticationResponse;
import com.lectura.apgmb.dtos.register.RegisterRequest;
import com.lectura.apgmb.dtos.register.RegisterResponse;
import com.lectura.apgmb.entities.secutiry.JwtToken;
import com.lectura.apgmb.entities.secutiry.User;
import com.lectura.apgmb.repositories.auth.JwtTokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthenticationService {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenRepository jwtTokenRepository;

    public RegisterResponse registerUser(RegisterRequest registerRequest) {
        User user = userService.registerUser(registerRequest);
        String jwt = "";
        if(!registerRequest.getRole().equals("CLIENT")){
            jwt = jwtService.generateToken(user, generateExtraClaims(user));
            saveUserToken(user, jwt);
        }

        return mapRegisterResponse(user, jwt);
    }

    private static RegisterResponse mapRegisterResponse(User user, String jwt) {
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setName(user.getName());
        registerResponse.setUserId(user.getId());
        registerResponse.setUsername(user.getUsername());
        registerResponse.setRole(user.getRole().getName());
        registerResponse.setJwt(jwt);
        return registerResponse;
    }

    private Map<String, Object> generateExtraClaims(User user) {

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getName());
        extraClaims.put("role", user.getRole().getName());
        extraClaims.put("authorities", user.getAuthorities());

        return extraClaims;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        System.out.println("credenciales: " + authenticationRequest.getUsername() + " " + authenticationRequest.getPassword());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword()
        );

        authenticationManager.authenticate(authentication);

        User user = userService.findByUsername(authenticationRequest.getUsername()).get();
        System.out.println("user " + user);

        String jwt = jwtService.generateToken(user, generateExtraClaims(user));

        saveUserToken(user, jwt);

        return mapDTO(jwt, user);
    }

    private void saveUserToken(User user, String jwt) {

        JwtToken token = new JwtToken();
        token.setToken(jwt);
        token.setUser(user);
        token.setExpirationDate(jwtService.extractExpiration(jwt));
        token.setValid(true);

        jwtTokenRepository.save(token);
    }

    public boolean validateToken(String jwt) {
        try {
            jwtService.extractUsername(jwt);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public void logout(HttpServletRequest request) {
        String jwt = jwtService.extractJwtFromRequest(request);

        if(jwt == null || !StringUtils.hasText(jwt)) return;

        Optional<JwtToken> token = jwtTokenRepository.findByToken(jwt);
        if(token.isPresent() && token.get().isValid()){
            token.get().setValid(false);
            jwtTokenRepository.save(token.get());
        }
    }

    private AuthenticationResponse mapDTO(String jwt, User user) {
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setJwt(jwt);
        authenticationResponse.setUserId(user.getId());

        return authenticationResponse;
    }
}
