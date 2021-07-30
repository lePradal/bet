package com.prads.bet.repository;

import com.prads.bet.enums.BetTimeStatus;
import com.prads.bet.enums.MatchStatus;
import com.prads.bet.models.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Long> {
    Optional<Match> findByTitle(String title);
    Optional<Match> findByStatus(MatchStatus status);
    Optional<Match> findByBeginDate(LocalDateTime beginDate);
    Optional<Match> findByBetTimeStatus(BetTimeStatus betTimeStatus);
}
