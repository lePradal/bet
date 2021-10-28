package com.prads.bet.controllers.dto;

import com.prads.bet.models.Bet;

import java.time.LocalDateTime;

public class BetDTO {
    private Long id;
    private Long matchId;
    private Double value;
    private Long userId;
    private Long teamId;
    private LocalDateTime creationDate;
    private String resultStatus;

    public BetDTO(Bet bet) {
        this.id = bet.getId();
        this.matchId = bet.getMatch().getId();
        this.value = bet.getValue();
        this.userId = bet.getUser().getId();
        this.teamId = bet.getTeam().getId();
        this.creationDate = bet.getCreationDate();
        this.resultStatus = bet.getResultStatus().getStatus();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMatch() {
        return matchId;
    }

    public void setMatch(Long matchId) {
        this.matchId = matchId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    public static BetDTO fromBet(Bet bet) {
        return new BetDTO(bet);
    }
}
