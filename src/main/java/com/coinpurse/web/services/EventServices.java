package com.coinpurse.web.services;

import com.coinpurse.web.model.Event;
import com.coinpurse.web.model.EventType;
import com.coinpurse.web.model.Purse;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface EventServices {
    Event createEvent(Purse purse, Event event);

    List<Event> findAllEvents();

    Event findByEventId(Long eventId);

    Event updateEvent(Long eventId, String comment, LocalDateTime date, EventType eventType,
                      BigDecimal delta, Long currencyId);

    void deleteEvent(Event event);

    List<Event> getAllEventsByDateAndCurrency(String currency);

    List<Event> getEventsByPurse(Purse purse);

}
