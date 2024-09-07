package com.coinpurse.web.services.impl;

import com.coinpurse.web.dto.PurseDto;
import com.coinpurse.web.model.Purse;
import com.coinpurse.web.repository.PurseRepository;
import com.coinpurse.web.services.PurseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurseServicesImpl implements PurseServices {
    private PurseRepository purseRepository;

    @Autowired
    public PurseServicesImpl(PurseRepository purseRepository) {
        this.purseRepository = purseRepository;
    }

    @Override
    public List<PurseDto> findAllPurses() {
        List<Purse> purses = purseRepository.findAll();
        return purses.stream().map((purse)->mapToPurseDto(purse)).collect(Collectors.toList());
    }

    private PurseDto mapToPurseDto(Purse purse) {
        PurseDto purseDto = PurseDto.builder()
                .id(purse.getId())
                .title(purse.getTitle())
                .creation(purse.getCreation())
                .amount(purse.getAmount())
                .currency(purse.getCurrency())
                .build();
        return purseDto;
    }
}
