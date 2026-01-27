package com.example.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class UserRequestdto {

    @Email
    private String email;

    @Size(min=8)
    private String password;   

    public UserRequestdto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserRequestdto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}