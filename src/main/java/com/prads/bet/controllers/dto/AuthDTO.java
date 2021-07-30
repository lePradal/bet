package com.prads.bet.controllers.dto;

public class AuthDTO {
    private Boolean valid;

    public AuthDTO(Boolean valid) {
        this.valid = valid;
    }

    public Boolean getValid() {
        return valid;
    }
}
