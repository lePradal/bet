package com.prads.bet.enums;

public enum RolesEnums {

    ROLE_OWNER("OWNER"),
    ROLE_ADMIN("ADMIN"),
    ROLE_BANK("BANK"),
    ROLE_BETTER("BETTER"),
    ROLE_USER("USER");

    private String role;

    RolesEnums(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
