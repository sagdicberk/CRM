package com.sgdcbrk.crm.business.concretes.auth;

import com.sgdcbrk.crm.business.abstracts.UserService;
import com.sgdcbrk.crm.dto.user.requests.LoginRequest;
import com.sgdcbrk.crm.dto.user.requests.RegisterRequest;
import com.sgdcbrk.crm.util.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public String authenticate(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();

            return jwtUtil.generateToken(userDetails.getEmail());

        } catch (AuthenticationException e) {
            return "Invalid email or password " + e.getMessage();
        }
    }

    public String register(RegisterRequest request) {
        try {
            userService.createUser(request);
            return "User registered successfully";
        } catch (Exception e) {
            return "Registry operating fall down. Try Again " + e.getMessage();
        }

    }
}
