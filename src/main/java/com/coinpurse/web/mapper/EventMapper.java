package com.coinpurse.web.mapper;

import com.coinpurse.web.dto.EventDto;
import com.coinpurse.web.model.Event;

public class EventMapper {
    public static Event mapToEvent(EventDto eventDto) {
        return Event.builder()
                .id(eventDto.getId())
                .comment(eventDto.getComment())
                .date(eventDto.getDate())
                .createdon(eventDto.getCreatedon())
                .updatedon(eventDto.getUpdatedon())
                .type(eventDto.getType())
                .delta(eventDto.getDelta())
                .finalvalue(eventDto.getFinalvalue())
                .purse(eventDto.getPurse())
                .build();
    }

    public static EventDto mapToEventDto(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .comment(event.getComment())
                .date(event.getDate())
                .createdon(event.getCreatedon())
                .updatedon(event.getUpdatedon())
                .type(event.getType())
                .delta(event.getDelta())
                .finalvalue(event.getFinalvalue())
                .purse(event.getPurse())
                .build();
    }
}
