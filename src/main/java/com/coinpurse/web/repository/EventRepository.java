package com.coinpurse.web.repository;

import com.coinpurse.web.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
