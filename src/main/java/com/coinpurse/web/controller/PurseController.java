package com.coinpurse.web.controller;

import com.coinpurse.web.domain.exceptions.ResourceNotFoundException;
import com.coinpurse.web.dto.purse.PurseDto;
import com.coinpurse.web.dto.purse.PurseListDto;
import com.coinpurse.web.mapper.PurseMapper;
import com.coinpurse.web.model.Purse;
import com.coinpurse.web.services.PurseServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.coinpurse.web.constants.ErrorMessages.PURSE_NOT_FOUND;

@RestController
@RequestMapping(value = "/api/purses")
public class PurseController {
    private final PurseServices purseServices;

    public PurseController(PurseServices purseServices) {
        this.purseServices = purseServices;
    }

    // Return CREATED on creation, server error on generic exception
    @PostMapping(produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public PurseDto createPurse(@RequestBody PurseDto purseDto) {
        Purse purse = PurseMapper.mapToPurse(purseDto);

        Purse response = purseServices.savePurse(purse);
        return PurseMapper.mapToPurseDto(response);
    }

    // Returns ok with list, or empty list
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<PurseListDto>> purseList() {
        List<PurseListDto> purses = purseServices.findAllPurses().stream().map(PurseMapper::mapToPurseListDto)
                .toList();
        return ResponseEntity.ok(purses);
    }

    // Returns ok with dto, or resource not found (ResourceNotFoundException)
    @GetMapping(value = "/{purseId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public PurseDto viewPurse(@PathVariable("purseId") Long purseId) {
        return PurseMapper.mapToPurseDto(purseServices.findPurseById(purseId));
    }


    // Returns ok with dto, otherwise not found (ResourceNotFoundException)
    @PutMapping(value = "/{purseId}", produces = "application/json")
    public ResponseEntity<PurseDto> updatePurse(@RequestBody PurseDto purse, @PathVariable Long purseId){
        return ResponseEntity.ok(
                    PurseMapper.mapToPurseDto(
                            purseServices.updatePurse(PurseMapper.mapToPurse(purse))
                    ));
    }

    // Return NO_CONTENT 204 if deleted, NOT FOUND if not found (exception)
    @DeleteMapping(value = "/{purseId}", produces = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePurse(@PathVariable("purseId") Long purseId) {
        purseServices.delete(purseId);
    }

}
