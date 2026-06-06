package com.coinpurse.web.mapper;

import com.coinpurse.web.dto.purse.PurseDto;
import com.coinpurse.web.dto.purse.PurseListDto;
import com.coinpurse.web.model.Purse;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class PurseMapper {
    public static Purse mapToPurse(PurseDto purse) {
        Purse purseDto = Purse.builder()
                .id(purse.getId())
                .title(purse.getTitle())
                .currency(purse.getCurrency())
                .description(purse.getDescription())
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
                .description(purse.getDescription())
                //.createdBy(purse.getCreatedBy())
                .events(Optional.ofNullable(purse.getEvents())
                        .orElseGet(Collections::emptyList)
                        .stream()
                        .filter(Objects::nonNull)
                        .map(EventMapper::mapToEventDto).collect(Collectors.toList()))
                .build();
        return purseDto;
    }

    public static PurseListDto mapToPurseListDto(Purse purse) {
        PurseListDto purseDto = PurseListDto.builder()
                .id(purse.getId())
                .title(purse.getTitle())
                .creation(purse.getCreation())
                .currency(purse.getCurrency())
                .description(purse.getDescription())
                .build();
        return purseDto;
    }
}
