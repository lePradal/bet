package com.prads.bet.controllers.form;

import com.prads.bet.models.Team;
import com.prads.bet.models.User;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TeamForm {

    @NotNull @NotEmpty @Length(min = 3)
    private String name;

    private String imageUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Team toTeam() {
        Team team = new Team(name);

        if (imageUrl != null || !imageUrl.isEmpty()) {
            team.setImageUrl(imageUrl);
        }

        return team;
    }
}
