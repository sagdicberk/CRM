package com.sgdcbrk.crm.business.abstracts;

import com.sgdcbrk.crm.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    void createUser(User user);
    void updateUser(long id, User user);
    void deleteUser(long id);
    User getUser(long id);
    List<User> getUsers();

}
