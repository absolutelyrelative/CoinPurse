package com.coinpurse.web.controller;

import com.coinpurse.web.domain.exceptions.ResourceNotFoundException;
import com.coinpurse.web.dto.purse.PurseDto;
import com.coinpurse.web.dto.purse.PurseListDto;
import com.coinpurse.web.mapper.PurseMapper;
import com.coinpurse.web.model.Purse;
import com.coinpurse.web.services.PurseServices;
import com.coinpurse.web.validation.OnCreate;
import com.coinpurse.web.validation.OnDelete;
import com.coinpurse.web.validation.OnUpdate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.coinpurse.web.constants.ErrorMessages.PURSE_NOT_FOUND;

@RestController
@RequestMapping(value = "/api/purses")
@Validated
public class PurseController {
    private final PurseServices purseServices;

    public PurseController(PurseServices purseServices) {
        this.purseServices = purseServices;
    }

    // Return CREATED on creation, server error on generic exception
    @PostMapping(produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public PurseDto createPurse(@RequestBody @Validated(OnCreate.class) PurseDto purseDto) {
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
    public PurseDto viewPurse(@PathVariable("purseId") @NotNull @Min(1) Long purseId) {
        return PurseMapper.mapToPurseDto(purseServices.findPurseById(purseId));
    }


    // Returns ok with dto, otherwise not found (ResourceNotFoundException)
    @PutMapping(value = "/{purseId}", produces = "application/json")
    public ResponseEntity<PurseDto> updatePurse(@RequestBody @Validated(OnUpdate.class) PurseDto purse,
                                                @PathVariable @NotNull @Min(1) Long purseId){
        return ResponseEntity.ok(
                    PurseMapper.mapToPurseDto(
                            purseServices.updatePurse(PurseMapper.mapToPurse(purse))
                    ));
    }

    // Return NO_CONTENT 204 if deleted, NOT FOUND if not found (exception)
    @DeleteMapping(value = "/{purseId}", produces = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePurse(@PathVariable("purseId") @NotNull @Min(1) Long purseId) {
        purseServices.delete(purseId);
    }

}
