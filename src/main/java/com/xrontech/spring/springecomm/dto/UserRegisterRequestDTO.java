package com.xrontech.spring.springecomm.dto;

import com.xrontech.spring.springecomm.model.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegisterRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String password;
//    private UserType userType;
}