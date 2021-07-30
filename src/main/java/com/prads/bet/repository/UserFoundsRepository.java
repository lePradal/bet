package com.prads.bet.repository;

import com.prads.bet.models.User;
import com.prads.bet.models.UserFounds;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserFoundsRepository extends JpaRepository<UserFounds, Long> {
    Optional<UserFounds> findByUser(User user);
}
