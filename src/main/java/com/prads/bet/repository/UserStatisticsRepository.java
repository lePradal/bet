package com.prads.bet.repository;

import com.prads.bet.models.User;
import com.prads.bet.models.UserStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserStatisticsRepository extends JpaRepository<UserStatistics, Long> {
    Optional<UserStatistics> findByUser(User user);
}
