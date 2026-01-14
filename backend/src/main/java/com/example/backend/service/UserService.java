package com.example.backend.service;

import org.springframework.stereotype.Service;

import com.example.backend.dto.UserRequestdto;
import com.example.backend.dto.UserResponsedto;
import com.example.backend.entity.User;
import com.example.backend.repo.UserRepo;

@Service
public class UserService {
    private UserRepo ur;

    public UserService(UserRepo ur) {
        this.ur = ur;
    }

    public UserResponsedto addUser(UserRequestdto u){
        User user=new User();
        user.setName(u.getName());
        user.setPassword(u.getPassword());
        User saved=ur.save(user);
        return new UserResponsedto(saved.getId(), saved.getName());
    }
    
}
