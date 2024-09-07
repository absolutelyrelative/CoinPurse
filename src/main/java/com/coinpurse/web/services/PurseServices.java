package com.coinpurse.web.services;

import com.coinpurse.web.dto.PurseDto;
import com.coinpurse.web.model.Purse;

import java.util.List;

public interface PurseServices {
    List<PurseDto> findAllPurses();
    Purse savePurse(Purse purse);

    PurseDto findPurseById(long purseId);

    void updatePurse(PurseDto purse);
}
