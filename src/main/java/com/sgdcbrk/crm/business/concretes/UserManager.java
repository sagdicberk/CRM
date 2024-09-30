package com.sgdcbrk.crm.business.concretes;

import com.sgdcbrk.crm.business.abstracts.UserService;
import com.sgdcbrk.crm.dto.user.requests.RegisterRequest;
import com.sgdcbrk.crm.dto.user.requests.UpdateUserRequest;
import com.sgdcbrk.crm.dto.user.responses.GetAllUserResponse;
import com.sgdcbrk.crm.model.Role;
import com.sgdcbrk.crm.model.User;
import com.sgdcbrk.crm.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserManager implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createUser(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setRoles(Set.of(Role.USER));
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void updateUser(long id, UpdateUserRequest request) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
        request.getEmail().ifPresent(existingUser::setEmail);
        request.getUsername().ifPresent(existingUser::setUsername);
        request.getPassword()
                .ifPresent(password -> existingUser.setPassword(passwordEncoder.encode(password)));

        userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.findById(id).ifPresent(userRepository::delete);
    }

    @Override
    public User getUser(long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
    }

    @Override
    public List<GetAllUserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public GetAllUserResponse convertToDto(User user) {
        GetAllUserResponse response = new GetAllUserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setRoles(user.getRoles());
        return response;
    }
}
