package com.prads.bet.models;

import javax.persistence.*;

@Entity
@Table(name="tb_user_founds")
public class UserFounds {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private UserFounds() {}

    public UserFounds(User user) {
        super();
        this.user = user;
    }
}
