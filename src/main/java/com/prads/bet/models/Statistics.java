package com.prads.bet.models;

public abstract class Statistics {

    private Integer winning;

    private Integer defeats;

    public Statistics() {}

    public Integer getWinning() {
        return winning;
    }

    public void setWinning(Integer winning) {
        this.winning = winning;
    }

    public Integer getDefeats() {
        return defeats;
    }

    public void setDefeats(Integer defeats) {
        this.defeats = defeats;
    }

    public void addWin() {
        this.winning++;
    }

    public void addDefeat() {
        this.defeats++;
    }
}
