package com.prads.bet.repository;

import com.prads.bet.models.BetPart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BetPartRepository extends JpaRepository<BetPart, Long> {
    Optional<BetPart> findByName(String name);
}
