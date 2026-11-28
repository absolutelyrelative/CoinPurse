package com.coinpurse.web.services.impl;

import com.coinpurse.web.domain.exceptions.ResourceNotFoundException;
import com.coinpurse.web.domain.exceptions.ValidationErrors;
import com.coinpurse.web.model.Currency;
import com.coinpurse.web.model.Event;
import com.coinpurse.web.model.EventType;
import com.coinpurse.web.model.Purse;
import com.coinpurse.web.repository.CurrencyRepository;
import com.coinpurse.web.repository.EventRepository;
import com.coinpurse.web.services.EventServices;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.coinpurse.web.constants.ErrorMessages.*;

@Service
public class EventServicesImpl implements EventServices {
    private final CurrencyRepository currencyRepository;
    private final EventRepository eventRepository;

    @Autowired
    public EventServicesImpl(EventRepository eventRepository,
                             CurrencyRepository currencyRepository) {
        this.eventRepository = eventRepository;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Event createEvent(Purse purse, Event event) {
        event.setPurse(purse);

        //Get last event by LocalDateTime date, sort to last, and update using that final value
        PageRequest pageRequest = PageRequest.of(0, 1);
        List<Event> lastEvent = eventRepository.findLastEventByPurse(purse, pageRequest);
        if(!lastEvent.isEmpty()) {
            BigDecimal previousValue = lastEvent.getFirst() == null ? BigDecimal.ZERO : lastEvent.getFirst().getFinalValue();
            event.setFinalValue(previousValue.add(event.getDelta()));
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
    @Transactional
    public Event updateEvent(Long eventId, String comment, LocalDateTime date, EventType eventType,
                             BigDecimal delta, Long currencyId) {
        Event existingEvent = findByEventId(eventId);

        new ValidationErrors()
                .check(date == null, DATE_FIELD, DATE_NULL)
                .check(delta == null, VALUE_FIELD, VALUE_NULL)
                .check(currencyId == null, CURRENCY_FIELD, CURRENCY_NULL)
                .throwIfAny();

        Currency existingCurrency = currencyRepository.findById(currencyId)
                        .orElseThrow(() -> new ResourceNotFoundException(CURRENCY_NOT_FOUND));

        existingEvent.setComment(comment);
        existingEvent.setDate(date);
        existingEvent.setType(eventType);
        existingEvent.setDelta(delta);
        existingEvent.setCurrency(existingCurrency);

        return existingEvent;
    }

    @Override
    public void deleteEvent(Event event) {
        if(event.getId() == null || !eventRepository.existsById(event.getId())) {
            throw new ResourceNotFoundException(EVENT_NOT_FOUND);
        }
        eventRepository.deleteById(event.getId());
    }

    @Override
    public List<Event> getAllEventsByDateAndCurrency(String currency) {
        List<Event> allEvents = currency == null ?
                eventRepository.findAll() :
                eventRepository.findAllByCurrency_Currency(currency, Pageable.unpaged());

        // Truncate to Days
        allEvents.forEach(event ->
            event.setDate(event.getDate().truncatedTo(ChronoUnit.DAYS))
        );

        return allEvents;
    }

    @Override
    public List<Event> getEventsByPurse(Purse purse) {
        return eventRepository.getEventsByPurseAndDateIsNotNullOrderByDateAsc(purse);
    }


}
