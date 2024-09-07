package com.coinpurse.web.services;

import com.coinpurse.web.dto.PurseDto;

import java.util.List;

public interface PurseServices {
    List<PurseDto> findAllPurses();
}
