package com.coinpurse.web.services.impl;

import com.coinpurse.web.domain.exceptions.ResourceNotFoundException;
import com.coinpurse.web.model.Event;
import com.coinpurse.web.model.Purse;
import com.coinpurse.web.repository.EventRepository;
import com.coinpurse.web.repository.PurseRepository;
import com.coinpurse.web.services.EventServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;

import static com.coinpurse.web.constants.ErrorMessages.EVENT_NOT_FOUND;
import static com.coinpurse.web.constants.ErrorMessages.PURSE_NOT_FOUND;

@Service
public class EventServicesImpl implements EventServices {
    private EventRepository eventRepository;

    @Autowired
    public EventServicesImpl(EventRepository eventRepository, PurseRepository purseRepository) {
        this.eventRepository = eventRepository;
    }

    // TODO: Should return 201 if created
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
        return eventRepository.findAll();
    }

    @Override
    public Event findByEventId(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException(EVENT_NOT_FOUND));
    }

    @Override
    public Event updateEvent(Event event) {
        if(event.getId() == null || eventRepository.existsById(event.getId())) {
            throw new ResourceNotFoundException(EVENT_NOT_FOUND);
        }
        return eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Event event) {
        if(event.getId() == null || eventRepository.existsById(event.getId())) {
            throw new ResourceNotFoundException(EVENT_NOT_FOUND);
        }
        eventRepository.deleteById(event.getId());
    }

    @Override
    public List<Event> getAllEventsByDateAndCurrency(String currency) {
        List<Event> allEvents =  eventRepository.findAllByCurrency(currency, null);

        // Truncate to Days
        allEvents.forEach(event ->
            event.setDate(event.getDate().truncatedTo(ChronoUnit.DAYS))
        );

        return allEvents;
    }

    @Override
    public List<Event> getEventsByPurse(Purse purse) {
        List<Event> events = eventRepository.getEventsByPurse(purse);
        events.sort(Comparator.comparing(Event::getDate));

        return events;
    }


}
