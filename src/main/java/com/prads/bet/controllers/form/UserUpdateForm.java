package com.prads.bet.controllers.form;

import org.hibernate.validator.constraints.Length;

public class UserUpdateForm {

    @Length(min = 3)
    private String name;

    private String email;

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
}
