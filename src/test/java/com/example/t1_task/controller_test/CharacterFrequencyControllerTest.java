package com.example.t1_task.controller_test;

import com.example.t1_task.controller.CharacterFrequencyController;
import com.example.t1_task.entity.CharacterFrequency;
import com.example.t1_task.service.CharacterFrequencyService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.*;

@WebMvcTest(CharacterFrequencyController.class)
public class CharacterFrequencyControllerTest {

    @InjectMocks
    private CharacterFrequencyController characterFrequencyController;

    @MockBean
    private CharacterFrequencyService characterFrequencyService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getCharacterFrequency_ReturnsCharacterFrequenciesSortedByFrequencyDescending() throws Exception {
        CharacterFrequency char1 = new CharacterFrequency("a", 5);
        CharacterFrequency char2 = new CharacterFrequency("c", 4);
        CharacterFrequency char3 = new CharacterFrequency("b", 1);
        List<CharacterFrequency> expectedResult = Arrays.asList(char1, char2, char3);

        when(characterFrequencyService.getCharacterFrequency(anyString())).thenReturn(expectedResult);

        MvcResult result = mockMvc.perform(get("/api/frequency").param("input", "aaaaabcccc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].character", is("a")))
                .andExpect(jsonPath("$[0].frequency", is(5)))
                .andExpect(jsonPath("$[1].character", is("c")))
                .andExpect(jsonPath("$[1].frequency", is(4)))
                .andExpect(jsonPath("$[2].character", is("b")))
                .andExpect(jsonPath("$[2].frequency", is(1)))
                .andReturn();
    }
    @Test
    public void getCharacterFrequency_ReturnsCharacterFrequenciesSortedByFrequencyDescendingEmptyCase() throws Exception {
        List<CharacterFrequency> expectedResult = Arrays.asList();

        when(characterFrequencyService.getCharacterFrequency(anyString())).thenReturn(expectedResult);

        MvcResult result = mockMvc.perform(get("/api/frequency").param("input", ""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)))
                .andReturn();
    }
}