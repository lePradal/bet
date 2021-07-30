package com.prads.bet.repository;

import com.prads.bet.models.Match;
import com.prads.bet.models.MatchFounds;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatchFoundsRepository extends JpaRepository<MatchFounds, Long> {
    Optional<MatchFounds> findByMatch(Match match);
}
