package com.prads.bet.enums;

public enum MatchStatus {
    TO_BEGIN("To begin"),
    STARTED("Started"),
    FINISHED("Finished"),
    CANCELED("Canceled");

    private String value;

    MatchStatus (String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
