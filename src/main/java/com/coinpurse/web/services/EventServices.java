package com.coinpurse.web.services;

import com.coinpurse.web.dto.EventDto;

public interface EventServices {
    void createEvent(Long purseId, EventDto eventDto);
}
