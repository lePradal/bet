package com.prads.bet.enums;

public enum MatchStatus {
    TO_BEGIN("To begin"),
    STARTED("Started"),
    FINISHED("Finished"),
    CANCELED("Canceled");

    private String description;

    MatchStatus (String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
