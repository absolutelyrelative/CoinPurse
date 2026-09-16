package com.coinpurse.web.services.impl;

import com.coinpurse.web.domain.exceptions.ResourceNotFoundException;
import com.coinpurse.web.model.Purse;
import com.coinpurse.web.repository.PurseRepository;
import com.coinpurse.web.repository.UserRepository;
import com.coinpurse.web.services.PurseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.coinpurse.web.constants.ErrorMessages.EVENT_NOT_FOUND;
import static com.coinpurse.web.constants.ErrorMessages.PURSE_NOT_FOUND;

@Service
public class PurseServicesImpl implements PurseServices {
    private PurseRepository purseRepository;

    @Autowired
    public PurseServicesImpl(PurseRepository purseRepository, UserRepository userRepository) {
        this.purseRepository = purseRepository;
    }

    @Override
    public List<Purse> findAllPurses() {
        return purseRepository.findAll();
    }

    @Override
    public Purse findPurseById(long purseId){
        return purseRepository.findById(purseId)
                .orElseThrow(() -> new ResourceNotFoundException(PURSE_NOT_FOUND));
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
        return purseRepository.searchPurse(query);
        return purses;
    }

}
