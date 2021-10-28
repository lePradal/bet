package com.prads.bet.repository;

import com.prads.bet.models.Team;
import com.prads.bet.models.TeamStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamStatisticsRepository extends JpaRepository<TeamStatistics, Long> {
    Optional<TeamStatistics> findByTeam(Team team);
}
