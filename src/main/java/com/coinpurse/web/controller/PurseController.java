package com.coinpurse.web.controller;

import com.coinpurse.web.dto.PurseDto;
import com.coinpurse.web.model.Purse;
import com.coinpurse.web.model.UserEntity;
import com.coinpurse.web.security.SecurityUtil;
import com.coinpurse.web.services.PurseServices;
import com.coinpurse.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PurseController {
    private PurseServices purseServices;
    private UserService userService;

    @Autowired
    public PurseController(PurseServices purseServices, UserService userService) {
        this.purseServices = purseServices;
        this.userService = userService;
    }

    @GetMapping("/purses")
    public String listPurses(Model model) {
        UserEntity user = new UserEntity();
        String sessionEmail = SecurityUtil.getSessionUser();
        if(sessionEmail != null) {
            user = userService.findByEmail(sessionEmail);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);


        List<PurseDto> purses = purseServices.findAllPurses();
        model.addAttribute("purses", purses);
        return "purses-list";
    }

    @GetMapping("/purses/new")
    public String createPurseForm(Model model) {
        Purse purse = new Purse();
        model.addAttribute("purse", purse);
        return "purses-create";
    }

    @PostMapping("/purses/new")
    public String savePurse(@ModelAttribute("purse") Purse purse) {
        purseServices.savePurse(purse);
        return "redirect:/purses";
    }

    @GetMapping("/purses/{purseId}/edit")
    public String editPurseForm(@PathVariable("purseId") long purseId, Model model) {
        PurseDto purse = purseServices.findPurseById(purseId);
        model.addAttribute("purse", purse);
        return "purses-edit";
    }

    //TODO: Implement validation with @Valid and hasErrors()
    @PostMapping("/purses/{purseId}/edit")
    public String updatePurse(@PathVariable("purseId") long purseId, @ModelAttribute("purse") PurseDto purse) {
        purse.setId(purseId);
        purseServices.updatePurse(purse);

        return "redirect:/purses";
    }

    @GetMapping("/purses/{purseId}")
    public String purseDetail(@PathVariable("purseId") long purseId, Model model) {
        UserEntity user = new UserEntity();
        PurseDto purseDto = purseServices.findPurseById(purseId);
        String sessionEmail = SecurityUtil.getSessionUser();
        if(sessionEmail != null) {
            user = userService.findByEmail(sessionEmail);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("purse", purseDto);
        return "purses-detail";
    }

    //TODO: Either admin or owner can do it
    @GetMapping("/purses/{purseId}/delete")
    public String deletePurse(@PathVariable("purseId") long purseId) {
        purseServices.delete(purseId);
        return "redirect:/purses";
    }

    @GetMapping("/purses/search")
    public String searchPurse(@RequestParam(value = "query") String query, Model model) {
        List<PurseDto> purses = purseServices.searchPurse(query);
        model.addAttribute("purses", purses);
        return "purses-list";
    }

    @RequestMapping("/")
    public String landingPage() {
        return "redirect:/purses";
    }
}
