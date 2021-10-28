package com.prads.bet.models;

import javax.persistence.*;

@Entity
@Table(name="tb_team_statistic")
public class TeamStatistics extends Statistics{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Team team;

    private TeamStatistics() {}

    public TeamStatistics(Team team) {
        super();
        this.team = team;
    }

    public Long getId() {
        return id;
    }

    public Team getUser() {
        return team;
    }

    public void setUser(Team team) {
        this.team = team;
    }
}
