package com.coinpurse.web.repository;

import com.coinpurse.web.model.Purse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PurseRepository extends JpaRepository<Purse, Long> {
    Optional<Purse> findByTitle(String url);
}
