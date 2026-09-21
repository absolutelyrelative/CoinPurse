package com.coinpurse.web.mapper;

import com.coinpurse.web.dto.event.EventDto;
import com.coinpurse.web.dto.purse.PurseDto;
import com.coinpurse.web.model.Event;
import com.coinpurse.web.model.Purse;

public class EventMapper {
    public static Event mapToEvent(EventDto eventDto) {
        return Event.builder()
                .id(eventDto.getId())
                .comment(eventDto.getComment())
                .date(eventDto.getDate())
                .type(eventDto.getType())
                .delta(eventDto.getDelta())
                .finalValue(eventDto.getFinalvalue())
                .purse(Purse.builder().id(eventDto.getPurseId()).build())
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
                .purseId(event.getPurse().getId())
                .currency(event.getCurrency())
                .build();
    }

    public static EventDto mapToEventListDto(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .comment(event.getComment())
                .date(event.getDate())
                .createdOn(event.getCreatedon())
                .updatedOn(event.getUpdatedon())
                .type(event.getType())
                .delta(event.getDelta())
                .finalvalue(event.getFinalValue())
                .currency(event.getCurrency())
                .build();
    }
}
