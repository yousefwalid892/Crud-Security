package com.example.CrudOperation.auth;

import com.example.CrudOperation.filter.JwtService;
import com.example.CrudOperation.model.Role;
import com.example.CrudOperation.model.UserEntity;
import com.example.CrudOperation.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public AuthenticationService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public String createUser(@RequestBody UserEntity user){
        user.setId(user.getId());
        user.setUsername(user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        userRepo.save(user);
        String jwtToken = jwtService.generateToken(user);
        return jwtToken;
    }
}
