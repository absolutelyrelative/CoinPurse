package com.coinpurse.web.controller;

import com.coinpurse.web.dto.event.EventDto;
import com.coinpurse.web.dto.purse.PurseDto;
import com.coinpurse.web.mapper.EventMapper;
import com.coinpurse.web.mapper.PurseMapper;
import com.coinpurse.web.model.Event;
import com.coinpurse.web.model.Purse;
import com.coinpurse.web.services.EventServices;
import com.coinpurse.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/events")
public class EventController {
    @Autowired
    private EventServices eventServices;
    @Autowired
    private UserService userService;

    @PostMapping(value = "/{purseId}/new", produces = "application/json")
    public ResponseEntity<EventDto> createEvent(@PathVariable("purseId") Long purseId, @RequestBody EventDto eventDto) {
        PurseDto purseDto = PurseDto.builder().id(purseId).build();
        Purse purse = PurseMapper.mapToPurse(purseDto);
        Event event = EventMapper.mapToEvent(eventDto);

        Event response = eventServices.createEvent(purse, event);
        return ResponseEntity.ok(EventMapper.mapToEventDto(response));
    }

    @GetMapping(value = "/list", produces = "application/json")
    public ResponseEntity<List<EventDto>> eventList() {
       List<EventDto> events = eventServices.findAllEvents().stream().map(EventMapper::mapToEventDto)
               .toList();
       return ResponseEntity.ok(events);
    }

    @GetMapping(value = "/{eventId}", produces = "application/json")
    public ResponseEntity<EventDto> viewEvent(@PathVariable("eventId") Long eventId) {
        EventDto dto = EventMapper.mapToEventDto(eventServices.findByEventId(eventId));
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "/{purseId}/edit", produces = "application/json")
    public ResponseEntity<String> updateEvent(@RequestBody EventDto event){
        eventServices.updatePurse(EventMapper.mapToEvent(event));
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{eventId}/delete", produces = "application/json")
    public ResponseEntity<String> deleteEvent(@PathVariable("eventId") Long eventId) {
        eventServices.deleteEvent(eventServices.findByEventId(eventId));
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/summary", produces = "application/json")
    public ResponseEntity<List<EventDto>> getSummary() {
        List<EventDto> events = eventServices.getAllEventsByDateAndCurrency("").stream().map(EventMapper::mapToEventDto)
                .toList();
        return ResponseEntity.ok(events);
    }
}
