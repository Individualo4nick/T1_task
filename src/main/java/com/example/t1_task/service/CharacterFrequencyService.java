package com.example.t1_task.service;

import com.example.t1_task.entity.CharacterFrequency;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CharacterFrequencyService {
    public List<CharacterFrequency> getCharacterFrequency(String input){
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (Character c : input.toCharArray()) {
            frequencyMap.put(c.toString(), frequencyMap.getOrDefault(c, 0) + 1);
        }
        List<CharacterFrequency> result = frequencyMap.entrySet().stream()
                .map(entry -> new CharacterFrequency(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparingInt(CharacterFrequency::getFrequency).reversed())
                .collect(Collectors.toList());

        return result;
    }
}
