package com.coinpurse.web.services.impl;

import com.coinpurse.web.dto.PurseDto;
import com.coinpurse.web.model.Purse;
import com.coinpurse.web.model.UserEntity;
import com.coinpurse.web.repository.PurseRepository;
import com.coinpurse.web.repository.UserRepository;
import com.coinpurse.web.security.SecurityUtil;
import com.coinpurse.web.services.PurseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.coinpurse.web.mapper.PurseMapper.mapToPurse;
import static com.coinpurse.web.mapper.PurseMapper.mapToPurseDto;

@Service
public class PurseServicesImpl implements PurseServices {
    private UserRepository userRepository;
    private PurseRepository purseRepository;

    @Autowired
    public PurseServicesImpl(PurseRepository purseRepository, UserRepository userRepository) {
        this.purseRepository = purseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<PurseDto> findAllPurses() {
        List<Purse> purses = purseRepository.findAll();
        return purses.stream().map((purse)->mapToPurseDto(purse)).collect(Collectors.toList());
    }

    @Override
    public PurseDto findPurseById(long purseId){
        Purse purse = purseRepository.findById(purseId).get();
        return mapToPurseDto(purse);
    }

    //Should this be a PurseDto?
    @Override
    public Purse savePurse(Purse purse) {
        String sessionEmail = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByEmail(sessionEmail);
        purse.setCreatedBy(user);
        return purseRepository.save(purse);
    }

    @Override
    public void updatePurse(PurseDto purseDto){
        String sessionEmail = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByEmail(sessionEmail);
        purseDto.setCreatedBy(user);
        Purse purse = mapToPurse(purseDto);
        purseRepository.save(purse);
    }

    @Override
    public void delete(long purseId) {
        purseRepository.deleteById(purseId);
    }

    @Override
    public List<PurseDto> searchPurse(String query) {
        List<Purse> purses = purseRepository.searchPurse(query);
        return purses.stream().map(purse -> mapToPurseDto(purse)).collect(Collectors.toList());
    }

}
