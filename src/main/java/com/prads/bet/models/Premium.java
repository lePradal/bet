package com.prads.bet.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="tb_premium")
public class Premium {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isActive;

    @ManyToOne
    private User user;

    private LocalDateTime purchaseDate = LocalDateTime.now();

    private LocalDateTime expiresDate;

    private Premium () {}

    public Premium(User user, Integer premiumDays) {
        this.user = user;
        this.expiresDate = purchaseDate.plusDays(premiumDays);
    }

    public Long getId() {
        return id;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public LocalDateTime getExpiresDate() {
        return expiresDate;
    }

}
