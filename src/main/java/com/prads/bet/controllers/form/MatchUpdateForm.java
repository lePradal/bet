package com.prads.bet.controllers.form;

import com.prads.bet.models.Team;

import java.time.LocalDateTime;

public class MatchUpdateForm {

    private String title;

    private LocalDateTime beginDate;

    private Long teamOne;

    private Long teamTwo;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDateTime beginDate) {
        this.beginDate = beginDate;
    }

    public Long getTeamOne() {
        return teamOne;
    }

    public void setTeamOne(Long teamOne) {
        this.teamOne = teamOne;
    }

    public Long getTeamTwo() {
        return teamTwo;
    }

    public void setTeamTwo(Long teamTwo) {
        this.teamTwo = teamTwo;
    }
}
