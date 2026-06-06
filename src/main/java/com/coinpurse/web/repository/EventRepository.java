package com.coinpurse.web.repository;

import com.coinpurse.web.model.Event;
import com.coinpurse.web.model.Purse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT e FROM Event e WHERE e.purse = :purse ORDER BY e.date DESC")
    List<Event> findLastEventByPurse(Purse purse, Pageable pageable);

    List<Event> findAllByCurrency(String currency, Pageable pageable);

    List<Event> getEventsByPurse(Purse purse);
}
