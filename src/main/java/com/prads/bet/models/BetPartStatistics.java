package com.prads.bet.models;

import javax.persistence.*;

@Entity
@Table(name="tb_bet_part_statistic")
public class BetPartStatistics extends Statistics{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private BetPart betPart;

    private BetPartStatistics() {}

    public BetPartStatistics(BetPart betPart) {
        super();
        this.betPart = betPart;
    }

    public Long getId() {
        return id;
    }

    public BetPart getUser() {
        return betPart;
    }

    public void setUser(BetPart betPart) {
        this.betPart = betPart;
    }
}
