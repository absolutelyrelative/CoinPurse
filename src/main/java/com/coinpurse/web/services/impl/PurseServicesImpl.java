package com.coinpurse.web.services.impl;

import com.coinpurse.web.model.Purse;
import com.coinpurse.web.repository.PurseRepository;
import com.coinpurse.web.repository.UserRepository;
import com.coinpurse.web.services.PurseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Purse> findAllPurses() {
        List<Purse> purses = purseRepository.findAll();
        return purses;
        //return purses.stream().map((purse)->mapToPurseDto(purse)).collect(Collectors.toList());
    }

    @Override
    public Purse findPurseById(long purseId){
        Purse purse = purseRepository.findById(purseId).get();
        return purse;
    }

    //Should this be a PurseDto?
    @Override
    public Purse savePurse(Purse purse) {
        return purseRepository.save(purse);
    }

    @Override
    public void updatePurse(Purse purse){
        purseRepository.save(purse);
    }

    @Override
    public void delete(long purseId) {
        purseRepository.deleteById(purseId);
    }

    @Override
    public List<Purse> searchPurse(String query) {
        List<Purse> purses = purseRepository.searchPurse(query);
        //return purses.stream().map(purse -> mapToPurseDto(purse)).collect(Collectors.toList());
        return purses;
    }

}
