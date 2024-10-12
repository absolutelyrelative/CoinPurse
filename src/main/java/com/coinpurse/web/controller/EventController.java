package com.coinpurse.web.controller;

import com.coinpurse.web.dto.EventDto;
import com.coinpurse.web.dto.PurseDto;
import com.coinpurse.web.model.Event;
import com.coinpurse.web.model.Purse;
import com.coinpurse.web.model.UserEntity;
import com.coinpurse.web.security.SecurityUtil;
import com.coinpurse.web.services.EventServices;
import com.coinpurse.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EventController {
    private EventServices eventServices;
    private UserService userService;


    @Autowired
    public EventController(EventServices eventServices, UserService userService) {
        this.userService = userService;
        this.eventServices = eventServices;
    }

    //TODO: Check why this isn't working anymore
    @GetMapping("/events/{purseId}/new")
    public String createEventForm(@PathVariable("purseId") Long purseId, Model model){
        Event event = new Event();
        model.addAttribute("purseId", purseId);
        model.addAttribute("event", event);
        return "events-create";
    }

    @PostMapping("/events/{purseId}")
    public String createEvent(@PathVariable("purseId") Long purseId, @ModelAttribute("event") EventDto eventDto, Model model) {
        eventServices.createEvent(purseId, eventDto);
        return "redirect:/purses/" + purseId;
    }

    @GetMapping("/events")
    public String eventList(Model model) {
        UserEntity user = new UserEntity();
        List<EventDto> events = eventServices.findAllEvents();
        String sessionEmail = SecurityUtil.getSessionUser();
        if(sessionEmail != null) {
            user = userService.findByEmail(sessionEmail);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("events", events);
        return "events-list";
    }

    @GetMapping("/events/{eventId}")
    public String viewEvent(@PathVariable("eventId") Long eventId, Model model) {
        UserEntity user = new UserEntity();
        String sessionEmail = SecurityUtil.getSessionUser();
        if(sessionEmail != null) {
            user = userService.findByEmail(sessionEmail);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        EventDto eventDto = eventServices.findByEventId(eventId);
        model.addAttribute("event", eventDto);
        model.addAttribute("purse", eventDto.getPurse());
        return "events-detail";
    }

    @GetMapping("/events/{eventId}/edit")
    public String editEventForm(@PathVariable("eventId") Long eventId, Model model) {
        EventDto eventDto = eventServices.findByEventId(eventId);
        model.addAttribute("event", eventDto);
        return "events-edit";
    }

    //TODO: Implement validation with @Valid and hasErrors()
    @PostMapping("/events/{purseId}/edit")
    public String updateEvent(@PathVariable("purseId") Long eventId, @ModelAttribute("event") EventDto event){
        event.setId(eventId);
        EventDto eventDto = eventServices.findByEventId(eventId);
        event.setPurse(eventDto.getPurse());
        eventServices.updatePurse(event);

        return "redirect:/events";
    }

    @GetMapping("/events/{eventId}/delete")
    public String deleteEvent(@PathVariable("eventId") Long eventId) {
        eventServices.deleteEvent(eventId);
        return "redirect:/events";
    }
}
