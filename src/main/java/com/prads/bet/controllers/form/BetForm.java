package com.prads.bet.controllers.form;

import com.prads.bet.models.User;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BetForm {

    @NotNull @NotEmpty @Length(min = 3)
    private String name;

    @NotNull @NotEmpty
    private String email;

    @NotNull @NotEmpty @Length(min = 8)
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public User toUser() {
        return new User(name, email, new BCryptPasswordEncoder().encode(password));
    }
}
