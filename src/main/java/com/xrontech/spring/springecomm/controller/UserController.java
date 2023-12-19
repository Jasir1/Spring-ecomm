package com.xrontech.spring.springecomm.controller;

import com.xrontech.spring.springecomm.dto.UserLoginRequestDTO;
import com.xrontech.spring.springecomm.dto.UserRegisterRequestDTO;
import com.xrontech.spring.springecomm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<?> userRegister(@RequestBody UserRegisterRequestDTO userRegisterRequestDTO){
        return userService.userRegister(userRegisterRequestDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody UserLoginRequestDTO userLoginRequestDTO){
        return userService.userLogin(userLoginRequestDTO);
    }

}
