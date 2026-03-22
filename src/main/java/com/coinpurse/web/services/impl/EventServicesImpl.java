package com.coinpurse.web.services.impl;

import com.coinpurse.web.model.Event;
import com.coinpurse.web.model.Purse;
import com.coinpurse.web.repository.EventRepository;
import com.coinpurse.web.repository.PurseRepository;
import com.coinpurse.web.services.EventServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServicesImpl implements EventServices {
    private EventRepository eventRepository;

    @Autowired
    public EventServicesImpl(EventRepository eventRepository, PurseRepository purseRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event createEvent(Purse purse, Event event) {
        event.setPurse(purse);

        //Get last event by LocalDateTime date, sort to last, and update using that final value
        PageRequest pageRequest = PageRequest.of(0, 1);
        List<Event> lastEvent = eventRepository.findLastEventByPurse(purse, pageRequest);
        if(!lastEvent.isEmpty()) {
            event.setFinalValue(lastEvent.get(0).getFinalValue() + event.getDelta());
        } else {
            event.setFinalValue(event.getDelta());
        }

        eventRepository.save(event);
        return event;
    }

    @Override
    public List<Event> findAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events;
    }

    @Override
    public Event findByEventId(Long eventId) {
        Event event = eventRepository.findById(eventId).get();
        return event;
    }

    @Override
    public void updatePurse(Event event) {
        eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Event event) {
        eventRepository.deleteById(event.getId());
    }


}
