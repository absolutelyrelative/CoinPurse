package com.coinpurse.web.mapper;

import com.coinpurse.web.dto.EventDto;
import com.coinpurse.web.model.Event;

public class EventMapper {
    public static Event mapToEvent(EventDto eventDto) {
        return Event.builder()
                .id(eventDto.getId())
                .comment(eventDto.getComment())
                .date(eventDto.getDate())
                .type(eventDto.getType())
                .delta(eventDto.getDelta())
                .finalValue(eventDto.getFinalvalue())
                .purse(eventDto.getPurse())
                .currency(eventDto.getCurrency())
                .build();
    }

    public static EventDto mapToEventDto(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .comment(event.getComment())
                .date(event.getDate())
                .createdOn(event.getCreatedon())
                .updatedOn(event.getUpdatedon())
                .type(event.getType())
                .delta(event.getDelta())
                .finalvalue(event.getFinalValue())
                .purse(event.getPurse())
                .currency(event.getCurrency())
                .build();
    }
}
