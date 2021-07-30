package com.prads.bet.repository;

import com.prads.bet.models.Bet;
import com.prads.bet.models.BetPart;
import com.prads.bet.models.Match;
import com.prads.bet.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BetRepository extends JpaRepository<Bet, Long> {
    Optional<Bet> findByUser(User user);
    Optional<Bet> findByMatchAndPart(Match match, BetPart part);
}
