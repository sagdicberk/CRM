package com.sgdcbrk.crm.business.concretes;

import com.sgdcbrk.crm.business.abstracts.UserService;
import com.sgdcbrk.crm.model.User;
import com.sgdcbrk.crm.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserManager implements UserService {
    private final UserRepository userRepository;


    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(long id, User user) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
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
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
