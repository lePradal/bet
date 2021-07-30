package com.prads.bet.repository;

import com.prads.bet.models.BetPart;
import com.prads.bet.models.BetPartStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BetPartStatisticsRepository extends JpaRepository<BetPartStatistics, Long> {
    Optional<BetPartStatistics> findByBetPart(BetPart betPart);
}
