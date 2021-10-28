package com.prads.bet.controllers.dto;

import com.prads.bet.models.Team;

public class TeamDTO {
    private Long id;
    private String name;
    private String imageUrl;

    public TeamDTO(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.imageUrl = team.getImageUrl();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public static TeamDTO fromTeam(Team team) {
        return new TeamDTO(team);
    }
}
