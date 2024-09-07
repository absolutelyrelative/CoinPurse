package com.coinpurse.web.controller;

import com.coinpurse.web.dto.PurseDto;
import com.coinpurse.web.model.Purse;
import com.coinpurse.web.services.PurseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PurseController {
    private PurseServices purseServices;

    @Autowired
    public PurseController(PurseServices purseServices) {
        this.purseServices = purseServices;
    }

    @GetMapping("/purses")
    public String listPurses(Model model) {
        List<PurseDto> purses = purseServices.findAllPurses();
        model.addAttribute("purses", purses);
        return "purses-list";
    }

    @GetMapping("/purses/new")
    public String createPurseForm(Model model){
        Purse purse = new Purse();
        model.addAttribute("purse", purse);
        return "purses-create";
    }

    @PostMapping("/purses/new")
    public String savePurse(@ModelAttribute("purse") Purse purse){
        purseServices.savePurse(purse);
        return "redirect:/purses";
    }
}
