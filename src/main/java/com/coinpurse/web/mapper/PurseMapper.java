package com.coinpurse.web.mapper;

import com.coinpurse.web.dto.PurseDto;
import com.coinpurse.web.model.Purse;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.coinpurse.web.mapper.EventMapper.mapToEventDto;

public class PurseMapper {
    public static Purse mapToPurse(PurseDto purse) {
        Purse purseDto = Purse.builder()
                .id(purse.getId())
                .title(purse.getTitle())
                .creation(purse.getCreation())
                .currency(purse.getCurrency())
                //.createdBy(purse.getCreatedBy())
                .build();

        return purseDto;
    }

    public static PurseDto mapToPurseDto(Purse purse) {
        PurseDto purseDto = PurseDto.builder()
                .id(purse.getId())
                .title(purse.getTitle())
                .creation(purse.getCreation())
                .currency(purse.getCurrency())
                //.createdBy(purse.getCreatedBy())
                .events(Optional.ofNullable(purse.getEvents())
                        .orElseGet(Collections::emptyList)
                        .stream()
                        .filter(Objects::nonNull)
                        .map(EventMapper::mapToEventDto).collect(Collectors.toList()))
                .build();
        return purseDto;
    }
}
