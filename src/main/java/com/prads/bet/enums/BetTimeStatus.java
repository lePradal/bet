package com.prads.bet.enums;

public enum BetTimeStatus {
    OPENED("Opened"),
    CLOSED("Closed");

    private String description;

    BetTimeStatus (String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
