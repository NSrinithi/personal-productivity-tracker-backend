package com.example.backend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.backend.config.JwtUtil;
import com.example.backend.dto.UserRequestdto;
import com.example.backend.dto.UserResponsedto;
import com.example.backend.entity.User;
import com.example.backend.exception.ResourceNotFound;
import com.example.backend.repo.UserRepo;

@Service
public class UserService {
    private UserRepo ur;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwt;

    public UserService(JwtUtil jwt, PasswordEncoder passwordEncoder, UserRepo ur) {
        this.jwt = jwt;
        this.passwordEncoder = passwordEncoder;
        this.ur = ur;
    }

    
   

    public UserResponsedto addUser(UserRequestdto u){
        User already=ur.findByEmail(u.getEmail());
        if(already==null){
            User user=new User();
        user.setEmail(u.getEmail());
        user.setPassword(passwordEncoder.encode(u.getPassword()));
        User saved=ur.save(user);
        return new UserResponsedto(saved.getEmail(), saved.getId());
        }
        return new UserResponsedto("", null);
        
    }

    public String login(UserRequestdto u){
        User user=ur.findByEmail(u.getEmail());
        if(!passwordEncoder.matches(u.getPassword(),user.getPassword())){
            throw new RuntimeException("Invalid credentials");
        }
        return jwt.generateToken(u.getEmail());
    }
    
}
