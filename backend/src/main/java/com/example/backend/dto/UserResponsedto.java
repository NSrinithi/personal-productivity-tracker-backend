package com.example.backend.dto;

public class UserResponsedto {
    private Long id;

    private String email;

    public UserResponsedto(String email, Long id) {
        this.email = email;
        this.id = id;
    }

    public UserResponsedto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
