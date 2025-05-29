package com.helloevents.dto;

import com.helloevents.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

    private String username;
    private String email;
    private String password;
    private User.Role role = User.Role.ADMIN; // Par défaut rôle CLIENT

}
