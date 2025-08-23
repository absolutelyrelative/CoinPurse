package com.coinpurse.web.services;

import com.coinpurse.web.dto.PurseDto;
import com.coinpurse.web.model.Purse;

import java.util.List;

public interface PurseServices {
    List<Purse> findAllPurses();
    Purse savePurse(Purse purse);

    Purse findPurseById(long purseId);

    void updatePurse(Purse purse);

    void delete(long purseId);
    List<Purse> searchPurse(String query);
}
