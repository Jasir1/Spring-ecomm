package com.xrontech.spring.springecomm.service;

import com.xrontech.spring.springecomm.dto.UserLoginRequestDTO;
import com.xrontech.spring.springecomm.dto.UserRegisterRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    ResponseEntity<?> userRegister(UserRegisterRequestDTO userRegisterRequestDTO);

    ResponseEntity<?> userLogin(UserLoginRequestDTO userLoginRequestDTO);

}
