package com.sgdcbrk.crm.dto.user.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    Optional<String> username;
    Optional<String> email;
    Optional<String> password;

}
