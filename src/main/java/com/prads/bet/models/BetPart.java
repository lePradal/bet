package com.prads.bet.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="tb_bet_part")
public class BetPart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDateTime creationDate = LocalDateTime.now();

    private BetPart() {}

    public BetPart(String name) {
        super();
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
}
