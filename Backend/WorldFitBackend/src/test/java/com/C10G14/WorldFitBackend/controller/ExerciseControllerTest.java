package com.C10G14.WorldFitBackend.controller;

import com.C10G14.WorldFitBackend.dto.exercise.ExerciseRequestDto;
import com.C10G14.WorldFitBackend.dto.exercise.ExerciseResponseDto;
import com.C10G14.WorldFitBackend.security.jwt.JwtService;
import com.C10G14.WorldFitBackend.service.impl.ExerciseServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ExerciseController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ExerciseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExerciseServiceImpl exerciseService;

    @MockBean
    private JwtService jwtService;

    private ExerciseResponseDto exercise1;
    private ExerciseResponseDto exercise2;
    private List<ExerciseResponseDto> exercises;
    private ExerciseResponseDto exerciseResponseDto;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void init() {
        exercise1 = new ExerciseResponseDto();
        exercise1.setId(1L);
        exercise1.setTitle("Exercise 1");
        exercise1.setDescription("This is exercise 1.");

        exercise2 = new ExerciseResponseDto();
        exercise2.setId(2L);
        exercise2.setTitle("Exercise 2");
        exercise2.setDescription("This is exercise 2.");

        exercises = Arrays.asList(exercise1, exercise2);
    }

    @Test
    @WithMockUser(roles = { "USER", "COACH" })
    public void getAllExercises_ReturnsExercises() throws Exception {
        given(exerciseService.getAllExercises()).willReturn(exercises);

        mockMvc.perform(get("/api/v1/exercises"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].title", Matchers.is("Exercise 1")))
                .andExpect(jsonPath("$[0].description", Matchers.is("This is exercise 1.")))
                .andExpect(jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(jsonPath("$[1].title", Matchers.is("Exercise 2")))
                .andExpect(jsonPath("$[1].description", Matchers.is("This is exercise 2.")));
    }

    @Test
    @WithMockUser(roles = { "USER", "COACH" })
    public void getExerciseById_ReturnsExercise() throws Exception {
        given(exerciseService.getExerciseById(1L)).willReturn(exercise1);

        mockMvc.perform(get("/api/v1/exercises/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.title", Matchers.is("Exercise 1")))
                .andExpect(jsonPath("$.description", Matchers.is("This is exercise 1.")));
    }

    @Test
    @WithMockUser(roles = { "USER", "COACH" })
    public void createExercise_ReturnsCreatedExercise() throws Exception {
        ExerciseRequestDto requestDto = new ExerciseRequestDto();
        requestDto.setTitle("New Exercise");
        requestDto.setDescription("This is a new exercise.");

        given(exerciseService.createExercise(requestDto)).willReturn(exercise1);

        mockMvc.perform(post("/api/v1/exercises")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.title", Matchers.is("Exercise 1")))
                .andExpect(jsonPath("$.description", Matchers.is("This is exercise 1.")));
    }


    @Test
    @WithMockUser(roles = { "USER", "COACH" })
    public void updateExercise_ReturnsOk() throws Exception {
        // Given
        ExerciseRequestDto requestDto = new ExerciseRequestDto();
        requestDto.setTitle("Updated Exercise");
        requestDto.setDescription("This is an updated exercise.");

        given(exerciseService.updateExercise(1L, requestDto)).willReturn(new ExerciseResponseDto());

        // When and Then
        mockMvc.perform(put("/api/v1/exercises/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = { "USER", "COACH" })
    public void deleteExerciseAsCoachReturnsOk() throws Exception {
        // given
        Long exerciseId = 1L;

        // when
        ResultActions result = mockMvc.perform(delete("/api/v1/exercises/" + exerciseId));

        // then
        result.andExpect(status().isOk())
                .andExpect(content().string("Exercise successfully deleted"));

        verify(exerciseService, times(1)).deleteExercise(exerciseId);
    }

    @Test
    @WithMockUser(roles = { "USER", "COACH" })
    public void deleteExerciseAsAdminReturnsOk() throws Exception {
        // given
        Long exerciseId = 1L;

        // when
        ResultActions result = mockMvc.perform(delete("/api/v1/exercises/" + exerciseId));

        // then
        result.andExpect(status().isOk())
                .andExpect(content().string("Exercise successfully deleted"));

        verify(exerciseService, times(1)).deleteExercise(exerciseId);
    }


}

