package com.jobportal.JobPortal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    @CrossOrigin
    private User signup(@RequestBody User user){
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        return userRepo.save(user);
    }

    @PostMapping("/login")
    @CrossOrigin
    private ResponseEntity<?> login(@RequestBody User user){
        Optional<User> findUser = userRepo.findByEmail(user.getEmail());
        if (findUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email Not Found");
        }
        else{
            boolean isMatch = passwordEncoder.matches(user.getPassword(), findUser.get().getPassword());
            if(isMatch){
                return ResponseEntity.status(HttpStatus.OK).body(findUser.get());
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Credentials");
            }
        }
    }
}
