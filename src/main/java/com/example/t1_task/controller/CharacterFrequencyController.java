package com.example.t1_task.controller;

import com.example.t1_task.entity.CharacterFrequency;
import com.example.t1_task.service.CharacterFrequencyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CharacterFrequencyController {

    private final CharacterFrequencyService characterFrequencyService;

    public CharacterFrequencyController(CharacterFrequencyService characterFrequencyService) {
        this.characterFrequencyService = characterFrequencyService;
    }

    @GetMapping("/frequency")
    public List<CharacterFrequency> getCharacterFrequency(@RequestParam String input) {
        return characterFrequencyService.getCharacterFrequency(input);
    }
}
