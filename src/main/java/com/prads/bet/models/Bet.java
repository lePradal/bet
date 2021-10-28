package com.prads.bet.models;

import com.prads.bet.enums.ResultStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="tb_bet")
public class Bet {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Match match;

    private Double value;

    @ManyToOne
    private User user;

    @OneToOne
    private Team team;

    private LocalDateTime creationDate = LocalDateTime.now();

    private ResultStatus resultStatus = ResultStatus.COMING;

    private Bet() {}

    public Bet(Match match, Double value, User user, Team team) {
        this.match = match;
        this.value = value;
        this.user = user;
        this.team = team;
    }

    public Long getId() {
        return id;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public ResultStatus getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(ResultStatus resultStatus) {
        this.resultStatus = resultStatus;
    }

}
