package com.prads.bet.models;

import javax.persistence.*;

@Entity
@Table(name="tb_user_statistic")
public class UserStatistics extends Statistics{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    private UserStatistics() {}

    public UserStatistics(User user) {
        super();
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
