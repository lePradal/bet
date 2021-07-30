package com.prads.bet.repository;

import com.prads.bet.models.Premium;
import com.prads.bet.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PremiumRepository extends JpaRepository<Premium, Long> {
    Optional<Premium> findByIsActive(Boolean isActive);
    Optional<Premium> findByUser(User user);
    Optional<Premium> findByExpiresDate(LocalDateTime expiresDate);
}
