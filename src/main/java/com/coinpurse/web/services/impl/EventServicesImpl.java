package com.coinpurse.web.services.impl;

import com.coinpurse.web.model.Event;
import com.coinpurse.web.model.Purse;
import com.coinpurse.web.repository.EventRepository;
import com.coinpurse.web.repository.PurseRepository;
import com.coinpurse.web.services.EventServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Event createEvent(Purse purse, Event event) {
        //Purse purse = purseRepository.findById(purse.getId()).get(); //TODO: This is considered bad practice, <Optional> may be null
        //Event event = eventRepository.findById(event.getId()).get();
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
