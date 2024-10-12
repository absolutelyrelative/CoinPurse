package com.coinpurse.web.repository;

import com.coinpurse.web.model.Event;
import com.coinpurse.web.model.Purse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT e FROM Event e WHERE e.purse = :purse ORDER BY e.date DESC")
    List<Event> findLastEventByPurse(Purse purse);
}
