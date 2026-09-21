package com.coinpurse.web.controller;

import com.coinpurse.web.domain.exceptions.ResourceNotFoundException;
import com.coinpurse.web.dto.event.EventDto;
import com.coinpurse.web.dto.purse.PurseDto;
import com.coinpurse.web.mapper.EventMapper;
import com.coinpurse.web.mapper.PurseMapper;
import com.coinpurse.web.model.Event;
import com.coinpurse.web.model.Purse;
import com.coinpurse.web.services.EventServices;
import com.coinpurse.web.validation.OnCreate;
import com.coinpurse.web.validation.OnUpdate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.coinpurse.web.constants.ErrorMessages.EVENT_NOT_FOUND;

@RestController
@RequestMapping(value = "/api/events")
@Validated
public class EventController {
    private final EventServices eventServices;

    @Autowired
    public EventController(EventServices eventServices) {
        this.eventServices = eventServices;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public EventDto createEvent(@RequestBody @Validated(OnCreate.class) EventDto eventDto) {
        PurseDto purseDto = PurseDto.builder().id(eventDto.getPurseId()).build();
        Purse purse = PurseMapper.mapToPurse(purseDto);
        Event event = EventMapper.mapToEvent(eventDto);

        Event response = eventServices.createEvent(purse, event);
        return EventMapper.mapToEventDto(response);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<EventDto>> eventList() {
       List<EventDto> events = eventServices.findAllEvents().stream().map(EventMapper::mapToEventListDto)
               .toList();
       return ResponseEntity.ok(events);
    }

    @GetMapping(value = "/{eventId}", produces = "application/json")
    public ResponseEntity<EventDto> getEvent(@PathVariable("eventId") @NotNull @Min(1) Long eventId) {
        Event event = eventServices.findByEventId(eventId);
        EventDto dto = EventMapper.mapToEventDto(event);
        return ResponseEntity.ok(dto);
    }

    @PutMapping(value = "/{purseId}/edit", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Event> updateEvent(@RequestBody @Validated(OnUpdate.class) EventDto event,
                                             @PathVariable @NotNull @Min(1) Long purseId){
        Event savedEvent = eventServices.updateEvent(EventMapper.mapToEvent(event));
        return ResponseEntity.ok(savedEvent);
    }

    // Return NO_CONTENT if deleted, NOT FOUND if not found
    @DeleteMapping(value = "/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable("eventId") @NotNull @Min(1) Long eventId) {
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
    public ResponseEntity<List<EventDto>> getEventListByPurse(@PathVariable @NotNull @Min(1) Long purseId) {
        PurseDto purseDto = PurseDto.builder().id(purseId).build();
        Purse purse = PurseMapper.mapToPurse(purseDto);
        List<Event>  events = eventServices.getEventsByPurse(purse);
        List<EventDto>  eventDtos = events.stream().map(EventMapper::mapToEventListDto)
                .toList();

        return ResponseEntity.ok(eventDtos);
    }
}
