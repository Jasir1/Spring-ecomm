package com.xrontech.spring.springecomm.service.ServiceImpl;

import com.xrontech.spring.springecomm.dto.UserLoginRequestDTO;
import com.xrontech.spring.springecomm.dto.UserRegisterRequestDTO;
import com.xrontech.spring.springecomm.model.User;
import com.xrontech.spring.springecomm.repository.UserRepository;
import com.xrontech.spring.springecomm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<?> userRegister(UserRegisterRequestDTO userRegisterRequestDTO) {
        if (userRegisterRequestDTO.getFirstName().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Please enter first name");
        }
        else if (userRegisterRequestDTO.getLastName().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Please enter last name");
        }
        else if (userRegisterRequestDTO.getEmail().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Please enter email");
        }
        else if (!Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?^`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])*$").matcher(userRegisterRequestDTO.getEmail()).matches()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email");
        }
        else if (userRegisterRequestDTO.getMobile().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Please enter mobile");
        }
        else if (!Pattern.compile("^07[01245678][0-9]{7}$").matcher(userRegisterRequestDTO.getMobile()).matches()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid mobile");
        }
        else if (userRegisterRequestDTO.getPassword().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Please enter password");
        }
        else if (userRepository.findByEmail(userRegisterRequestDTO.getEmail().toLowerCase()).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exist");
        }
        else if (userRepository.findByEmail(userRegisterRequestDTO.getMobile()).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mobile already exist");
        }
        else{


            User user = new User();
            user.setFirstName(userRegisterRequestDTO.getFirstName());
            user.setLastName(userRegisterRequestDTO.getLastName());
            user.setEmail(userRegisterRequestDTO.getEmail().toLowerCase());
            user.setMobile(userRegisterRequestDTO.getMobile());
            user.setPassword(userRegisterRequestDTO.getPassword());
//            user.setUserType(userRegisterRequestDTO.getUserType());

            userRepository.save(user);

        }
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully!");
    }

    @Override
    public ResponseEntity<?> userLogin(UserLoginRequestDTO userLoginRequestDTO) {
        if (userLoginRequestDTO.getEmail().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Please enter email");
        }
        else if (userLoginRequestDTO.getPassword().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Please enter password");
        }
        else if (userRepository.findByEmailAndPassword(userLoginRequestDTO.getEmail(), userLoginRequestDTO.getPassword()).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid credentials");
        }
        else{
//            Optional<User> optionalUser = userRepository.findByEmailAndPassword(userLoginRequestDTO.getEmail(), userLoginRequestDTO.getPassword());
//            if (optionalUser.isEmpty()){
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid credentials");
//            }else{
//
//            }
//
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User logged successfully!");
    }
}
