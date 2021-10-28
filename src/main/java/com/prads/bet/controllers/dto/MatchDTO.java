package com.prads.bet.controllers.dto;

import com.prads.bet.models.Match;

import java.time.LocalDateTime;

public class MatchDTO {
    private Long id;
    private String title;
    private String status;
    private LocalDateTime creationDate;
    private LocalDateTime beginDate;
    private Long teamOneId;
    private Long teamTwoId;
    private String betTimeStatus;

    public MatchDTO(Match match) {
        this.id = match.getId();
        this.title = match.getTitle();
        this.status = match.getStatus().getValue();
        this.creationDate = match.getCreationDate();
        this.beginDate = match.getBeginDate();
        this.creationDate = match.getCreationDate();
        this.teamOneId = match.getTeamOne().getId();
        this.teamTwoId = match.getTeamTwo().getId();
        this.betTimeStatus = match.getBetTimeStatus().getValue();
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getBeginDate() {
        return beginDate;
    }

    public Long getTeamOneId() {
        return teamOneId;
    }

    public Long getTeamTwoId() {
        return teamTwoId;
    }

    public String getBetTimeStatus() {
        return betTimeStatus;
    }

    public static MatchDTO fromMatch(Match match) {
        return new MatchDTO(match);
    }
}
