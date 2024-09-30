package com.sgdcbrk.crm.controller;

import com.sgdcbrk.crm.business.abstracts.UserService;
import com.sgdcbrk.crm.business.concretes.auth.UserDetailsImp;
import com.sgdcbrk.crm.dto.user.requests.UpdateUserRequest;
import com.sgdcbrk.crm.dto.user.responses.GetAllUserResponse;
import com.sgdcbrk.crm.dto.user.responses.GetCurrentUserResponse;
import com.sgdcbrk.crm.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable long id, @RequestBody UpdateUserRequest updateUserRequest){
        try{
            userService.updateUser(id, updateUserRequest);
            return ResponseEntity.ok("User updated successfully");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id){
        try{
            userService.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<User> getUser(@PathVariable long id){
        try {
            return ResponseEntity.ok(userService.getUser(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }


    @GetMapping("/current")
    public ResponseEntity<GetCurrentUserResponse> getCurrentUser(@AuthenticationPrincipal UserDetailsImp userDetails){
        if (userDetails != null) {

            GetCurrentUserResponse response = new GetCurrentUserResponse();
            response.setEmail(userDetails.getEmail());
            response.setUsername(userDetails.getUsername());

            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }


    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<GetAllUserResponse>> getAllUserResponseResponse(){
        List<GetAllUserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
