package com.coinpurse.web.repository;

import com.coinpurse.web.model.Purse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PurseRepository extends JpaRepository<Purse, Long> {
    Optional<Purse> findByTitle(String url);
    @Query("SELECT p from Purse p WHERE p.title LIKE CONCAT('%', :query, '%')")
    List<Purse> searchPurse(String query);
}
