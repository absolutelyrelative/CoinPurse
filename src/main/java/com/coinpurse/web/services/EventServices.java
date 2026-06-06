package com.coinpurse.web.services;

import com.coinpurse.web.model.Event;
import com.coinpurse.web.model.Purse;

import java.util.List;

public interface EventServices {
    Event createEvent(Purse purse, Event event);

    List<Event> findAllEvents();

    Event findByEventId(Long eventId);

    void updatePurse(Event event);

    void deleteEvent(Event event);

    List<Event> getAllEventsByDateAndCurrency(String currency);

    List<Event> getEventsByPurse(Purse purse);

}
