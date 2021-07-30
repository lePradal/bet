package com.prads.bet.models;

import javax.persistence.*;

@Entity
@Table(name="tb_match_founds")
public class MatchFounds {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Match match;

    private MatchFounds() {}

    public MatchFounds(Match match) {
        super();
        this.match = match;
    }
}
