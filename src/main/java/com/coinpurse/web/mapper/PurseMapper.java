package com.coinpurse.web.mapper;

import com.coinpurse.web.dto.PurseDto;
import com.coinpurse.web.model.Purse;

import java.util.stream.Collectors;

import static com.coinpurse.web.mapper.EventMapper.mapToEventDto;

public class PurseMapper {
    public static Purse mapToPurse(PurseDto purse) {
        Purse purseDto = Purse.builder()
                .id(purse.getId())
                .title(purse.getTitle())
                .amount(purse.getAmount())
                .creation(purse.getCreation())
                .currency(purse.getCurrency())
                .createdBy(purse.getCreatedBy())
                .build();

        return purseDto;
    }

    public static PurseDto mapToPurseDto(Purse purse) {
        PurseDto purseDto = PurseDto.builder()
                .id(purse.getId())
                .title(purse.getTitle())
                .creation(purse.getCreation())
                .amount(purse.getAmount())
                .currency(purse.getCurrency())
                .createdBy(purse.getCreatedBy())
                .events(purse.getEvents().stream().map((event) -> mapToEventDto(event)).collect(Collectors.toList()))
                .build();
        return purseDto;
    }
}
