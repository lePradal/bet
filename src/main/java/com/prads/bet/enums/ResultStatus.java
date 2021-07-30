package com.prads.bet.enums;

public enum ResultStatus {
    WIN("Win"),
    DEFEAT("Defeat"),
    COMING("Coming");

    private String description;

    ResultStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
