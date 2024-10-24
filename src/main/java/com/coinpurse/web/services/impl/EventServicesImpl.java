package com.coinpurse.web.services.impl;

import com.coinpurse.web.dto.EventDto;
import com.coinpurse.web.model.Event;
import com.coinpurse.web.model.Purse;
import com.coinpurse.web.repository.EventRepository;
import com.coinpurse.web.repository.PurseRepository;
import com.coinpurse.web.services.EventServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static com.coinpurse.web.mapper.EventMapper.mapToEvent;
import static com.coinpurse.web.mapper.EventMapper.mapToEventDto;

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

        //Get last event by LocalDateTime date, sort to last, and update using that final value
        List<Event> lastEvent = eventRepository.findLastEventByPurse(purse);
        if(!lastEvent.isEmpty()) {
            //TODO: Implement Exception in case lastEvent is not found
            event.setFinalvalue(lastEvent.get(0).getFinalvalue() + event.getDelta());
        } else {
            event.setFinalvalue(event.getDelta());
        }

        eventRepository.save(event);
    }

    @Override
    public List<EventDto> findAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(event -> mapToEventDto(event)).collect(Collectors.toList());
    }

    @Override
    public EventDto findByEventId(Long eventId) {
        Event event = eventRepository.findById(eventId).get();
        return mapToEventDto(event);
    }

    @Override
    public void updatePurse(EventDto eventDto) {
        Event event = mapToEvent(eventDto);
        eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }


}
