package com.prads.bet.enums;

public enum ResultStatus {
    WIN("Win"),
    DEFEAT("Defeat"),
    COMING("Coming");

    private String status;

    ResultStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
