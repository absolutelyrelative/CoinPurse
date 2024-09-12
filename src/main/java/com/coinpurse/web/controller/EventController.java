package com.coinpurse.web.controller;

import com.coinpurse.web.dto.EventDto;
import com.coinpurse.web.model.Event;
import com.coinpurse.web.services.EventServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EventController {
    private EventServices eventServices;


    @Autowired
    public EventController(EventServices eventServices) {
        this.eventServices = eventServices;
    }

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
}