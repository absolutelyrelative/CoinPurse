package com.coinpurse.web.services;

import com.coinpurse.web.dto.EventDto;

import java.util.List;

public interface EventServices {
    void createEvent(Long purseId, EventDto eventDto);
    List<EventDto> findAllEvents();

    EventDto findByEventId(Long eventId);

    void updatePurse(EventDto eventDto);

    void deleteEvent(Long eventId);
}
