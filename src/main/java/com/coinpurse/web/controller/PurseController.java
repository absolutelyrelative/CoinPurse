package com.coinpurse.web.controller;

import com.coinpurse.web.domain.exceptions.ResourceNotFoundException;
import com.coinpurse.web.dto.purse.PurseDto;
import com.coinpurse.web.dto.purse.PurseListDto;
import com.coinpurse.web.mapper.PurseMapper;
import com.coinpurse.web.model.Purse;
import com.coinpurse.web.services.PurseServices;
import com.coinpurse.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping(value = "/new", produces = "application/json", consumes = "application/json")
    public ResponseEntity<PurseDto> createPurse(@RequestBody PurseDto purseDto) {
        Purse purse = PurseMapper.mapToPurse(purseDto);

        Purse response = purseServices.savePurse(purse);
        return ResponseEntity.ok(PurseMapper.mapToPurseDto(response));
    }

    @GetMapping(value = "/list", produces = "application/json")
    public ResponseEntity<List<PurseListDto>> purseList() {
        List<PurseListDto> purses = purseServices.findAllPurses().stream().map(PurseMapper::mapToPurseListDto)
                .toList();
        return ResponseEntity.ok(purses);
    }

    @GetMapping(value = "/{purseId}", produces = "application/json")
    public ResponseEntity<PurseDto> viewPurse(@PathVariable("purseId") Long purseId) {
        Purse purse = purseServices.findPurseById(purseId);
        if(purse == null) throw new ResourceNotFoundException(PURSE_NOT_FOUND);
        PurseDto dto = PurseMapper.mapToPurseDto(purse);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "/{purseId}/edit", produces = "application/json")
    public ResponseEntity<String> updatePurse(@RequestBody PurseDto purse, @PathVariable Long purseId){
        purseServices.updatePurse(PurseMapper.mapToPurse(purse));
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{purseId}/delete", produces = "application/json")
    public ResponseEntity<String> deletePurse(@PathVariable("purseId") Long purseId) {
        purseServices.delete(purseId);
        return ResponseEntity.ok().build();
    }

}
