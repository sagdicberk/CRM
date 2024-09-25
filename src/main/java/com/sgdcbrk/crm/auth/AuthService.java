package com.sgdcbrk.crm.auth;

import com.sgdcbrk.crm.business.abstracts.UserService;
import com.sgdcbrk.crm.dto.requests.LoginRequest;
import com.sgdcbrk.crm.dto.requests.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public String authenticate(LoginRequest request) {
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return "Logged in as " + userDetails.getUsername();
        }catch(AuthenticationException e){
            return "Invalid email or password " + e.getMessage();
        }
    }

    public String register(RegisterRequest request) {
        try{
            userService.createUser(request);
            return "User registered successfully";
        }catch (Exception e){
            return "Registry operating fall down. Try Again " + e.getMessage();
        }

    }
}
