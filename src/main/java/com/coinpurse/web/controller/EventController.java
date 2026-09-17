package com.coinpurse.web.controller;

import com.coinpurse.web.domain.exceptions.ResourceNotFoundException;
import com.coinpurse.web.dto.event.EventDto;
import com.coinpurse.web.dto.purse.PurseDto;
import com.coinpurse.web.mapper.EventMapper;
import com.coinpurse.web.mapper.PurseMapper;
import com.coinpurse.web.model.Event;
import com.coinpurse.web.model.Purse;
import com.coinpurse.web.services.EventServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.coinpurse.web.constants.ErrorMessages.EVENT_NOT_FOUND;

@RestController
@RequestMapping(value = "/api/events")
public class EventController {
    private final EventServices eventServices;

    @Autowired
    public EventController(EventServices eventServices) {
        this.eventServices = eventServices;
    }

    @PostMapping(value = "/new", produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public EventDto createEvent(@RequestBody EventDto eventDto) {
        PurseDto purseDto = PurseDto.builder().id(eventDto.getPurse().getId()).build();
        Purse purse = PurseMapper.mapToPurse(purseDto);
        Event event = EventMapper.mapToEvent(eventDto);

        Event response = eventServices.createEvent(purse, event);
        return EventMapper.mapToEventDto(response);
    }

    @GetMapping(value = "/list", produces = "application/json")
    public ResponseEntity<List<EventDto>> eventList() {
       List<EventDto> events = eventServices.findAllEvents().stream().map(EventMapper::mapToEventListDto)
               .toList();
       return ResponseEntity.ok(events);
    }

    @GetMapping(value = "/{eventId}", produces = "application/json")
    public ResponseEntity<EventDto> viewEvent(@PathVariable("eventId") Long eventId) {
        Event event = eventServices.findByEventId(eventId);
        EventDto dto = EventMapper.mapToEventDto(event);
        return ResponseEntity.ok(dto);
    }

    @PutMapping(value = "/{purseId}/edit", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Event> updateEvent(@RequestBody EventDto event, @PathVariable Long purseId){
        Event savedEvent = eventServices.updateEvent(EventMapper.mapToEvent(event));
        return ResponseEntity.ok(savedEvent);
    }

    // Return NO_CONTENT if deleted, NOT FOUND if not found
    @DeleteMapping(value = "/{eventId}/delete")
    public ResponseEntity<Void> deleteEvent(@PathVariable("eventId") Long eventId) {
        eventServices.deleteEvent(EventMapper.mapToEvent(new EventDto(eventId)));
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/summary", produces = "application/json")
    public ResponseEntity<List<EventDto>> getSummary() {
        List<EventDto> events = eventServices.getAllEventsByDateAndCurrency("").stream().map(EventMapper::mapToEventDto)
                .toList();
        return ResponseEntity.ok(events);
    }

    @GetMapping(value = "/purse/{purseId}", produces = "application/json")
    public ResponseEntity<List<EventDto>> getEventListByPurse(@PathVariable Long purseId) {
        PurseDto purseDto = PurseDto.builder().id(purseId).build();
        Purse purse = PurseMapper.mapToPurse(purseDto);
        List<Event>  events = eventServices.getEventsByPurse(purse);
        List<EventDto>  eventDtos = events.stream().map(EventMapper::mapToEventListDto)
                .toList();

        return ResponseEntity.ok(eventDtos);
    }
}
