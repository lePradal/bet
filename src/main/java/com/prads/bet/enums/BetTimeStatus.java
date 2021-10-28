package com.prads.bet.enums;

public enum BetTimeStatus {
    OPENED("Opened"),
    CLOSED("Closed");

    private String value;

    BetTimeStatus (String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
