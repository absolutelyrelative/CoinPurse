package com.coinpurse.web.services.impl;

import com.coinpurse.web.dto.EventDto;
import com.coinpurse.web.model.Event;
import com.coinpurse.web.model.Purse;
import com.coinpurse.web.repository.EventRepository;
import com.coinpurse.web.repository.PurseRepository;
import com.coinpurse.web.services.EventServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServicesImpl implements EventServices {
    private EventRepository eventRepository;
    private PurseRepository purseRepository;

    @Autowired
    public EventServicesImpl(EventRepository eventRepository, PurseRepository purseRepository) {
        this.eventRepository = eventRepository;
        this.purseRepository = purseRepository;
    }

    @Override
    public void createEvent(Long purseId, EventDto eventDto) {
        Purse purse = purseRepository.findById(purseId).get(); //TODO: This is considered bad practice, <Optional> may be null
        Event event = mapToEvent(eventDto);
        event.setPurse(purse);
        eventRepository.save(event);
    }

    private Event mapToEvent(EventDto eventDto) {
        return Event.builder()
                .id(eventDto.getId())
                .comment(eventDto.getComment())
                .date(eventDto.getDate())
                .createdon(eventDto.getCreatedon())
                .updatedon(eventDto.getUpdatedon())
                .type(eventDto.getType())
                .delta(eventDto.getDelta())
                .finalvalue(eventDto.getFinalvalue())
                .build();
    }
}
