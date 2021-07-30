package com.prads.bet.controllers.dto;

import com.prads.bet.models.User;

import java.time.LocalDateTime;

public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime creationDate;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.creationDate = user.getRegistrationDate();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public static UserDTO fromUser(User user) {
        return new UserDTO(user);
    }
}
