package com.C10G14.WorldFitBackend.controller;

import java.util.*;

import com.C10G14.WorldFitBackend.dto.exercise.Exercise_RoutineRequestDto;
import com.C10G14.WorldFitBackend.dto.exercise.Exercise_RoutineResponseDto;
import com.C10G14.WorldFitBackend.dto.routine.RoutineRequestDto;
import com.C10G14.WorldFitBackend.dto.routine.RoutineResponseDto;
import com.C10G14.WorldFitBackend.entity.*;
import com.C10G14.WorldFitBackend.enumeration.ESex;
import com.C10G14.WorldFitBackend.security.jwt.JwtService;
import com.C10G14.WorldFitBackend.service.UserService;
import com.C10G14.WorldFitBackend.service.impl.RoutineServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import com.C10G14.WorldFitBackend.entity.User;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(controllers = RoutineController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class RoutineControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @MockBean
    private RoutineServiceImpl routineService;
    @MockBean
    private JwtService jwtService;
    @Autowired
    ObjectMapper mapper;
    private User user1;
    private Routine routine1;
    private Exercise exercise1;
    private Exercise_Routine exercise_routine;
    private RoutineRequestDto requestDto;
    private RoutineResponseDto responseDto;
    private Exercise_RoutineResponseDto exerciseRoutineResponseDto;
    private Exercise_RoutineRequestDto exerciseRoutineRequestDto;

    @BeforeEach
    public void init() throws JsonProcessingException {

        user1 = User.builder()
                .email("user1@example.com")
                .password("password")
                .sex(ESex.MALE)
                .age(25)
                .weight(65.0)
                .height(1.55)
                .profileImg("https://avatars.githubusercontent.com/u/83373185?v=4")
                .build();

        exercise1 = new Exercise();
        exercise1.setId(1L);
        exercise1.setTitle("Test Exercise");
        exercise1.setDescription("Test Exercise description");
        exercise1.setMedia("http://asdasd.com");

        exercise_routine = new Exercise_Routine();
        exercise_routine.setRoutine(routine1);
        exercise_routine.setExercise(exercise1);
        exercise_routine.setSeries(1);
        exercise_routine.setQuantity(1);
        exercise_routine.setRepetitions(1);

        Set<Exercise_Routine> exercises = new HashSet<>();
        exercises.add(exercise_routine);

        routine1 = new Routine();
        routine1.setId(1L);
        routine1.setUser(user1);
        routine1.setTitle("Monday");
        routine1.setExercises(exercises);

        Set<Routine> routines = new HashSet<>();
        routines.add(routine1);

        user1.setRoutines(routines);

        requestDto = new RoutineRequestDto();
        requestDto.setUserId(1L);
        requestDto.setTitle("Monday");

        exerciseRoutineResponseDto = new Exercise_RoutineResponseDto();
        exerciseRoutineResponseDto.setId(1L);
        exerciseRoutineResponseDto.setTitle(exercise1.getTitle());
        exerciseRoutineResponseDto.setDescription(exercise1.getDescription());
        exerciseRoutineResponseDto.setMedia(exercise1.getMedia());
        exerciseRoutineResponseDto.setRepetitions(exercise_routine.getRepetitions());
        exerciseRoutineResponseDto.setType("Routine");
        exerciseRoutineResponseDto.setSeriesNumber(5);

        Set<Exercise_RoutineResponseDto> exerciseRoutineResponseDtoSet = new HashSet<>();

        exerciseRoutineResponseDtoSet.add(exerciseRoutineResponseDto);

        responseDto = new RoutineResponseDto();
        responseDto.setId(1L);
        responseDto.setTitle(routine1.getTitle());
        responseDto.setExercises(exerciseRoutineResponseDtoSet);

        exerciseRoutineRequestDto = new Exercise_RoutineRequestDto();
        exerciseRoutineRequestDto.setExerciseId(1L);
        exerciseRoutineRequestDto.setQuantity(1);
        exerciseRoutineRequestDto.setRepetitions(1);
        exerciseRoutineRequestDto.setQuantity(1);

    }

    @Test
    @WithMockUser(roles = {"ADMIN", "COUCH"})
    public void createRoutine_shouldReturnCreatedRoutine() throws Exception {

        when(routineService.createRoutine(requestDto)).thenReturn(responseDto);

        mockMvc.perform(post("/api/v1/routines")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Monday"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "COUCH"})
    public void getAllRoutines_shouldReturnListOfRoutines() throws Exception {

        List<RoutineResponseDto> responseDtoList = Arrays.asList(responseDto);

        when(routineService.getAllRoutines()).thenReturn(responseDtoList);

        mockMvc.perform(get("/api/v1/routines"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Monday"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "COUCH"})
    public void getRoutineById_shouldReturnCorrectRoutine() throws Exception {

        when(routineService.getRoutineById(1L)).thenReturn(responseDto);

        mockMvc.perform(get("/api/v1/routines/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Monday"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "COUCH"})
    public void updateRoutine_shouldReturnRoutineUpdated() throws Exception {
        RoutineRequestDto request = new RoutineRequestDto();
        request.setTitle("New Title");

        RoutineResponseDto response = new RoutineResponseDto();
        response.setId(1L);
        response.setTitle("New Title");

        when(routineService.updateRoutine(1L, request)).thenReturn(response);

        mockMvc.perform(put("/api/v1/routines/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.title", Matchers.is("New Title")));
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "COUCH"})
    public void updateRoutine_shouldReturnNothing() throws Exception {
        mockMvc.perform(delete("/api/v1/routines/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Routine successfully deleted"));

        verify(routineService, times(1)).deleteRoutine(1L);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void addExercise_shouldReturnOk() throws Exception {
        Exercise_RoutineRequestDto exerciseRequest = new Exercise_RoutineRequestDto();
        exerciseRequest.setExerciseId(1L);
        exerciseRequest.setSeries(2);
        exerciseRequest.setQuantity(3);
        exerciseRequest.setRepetitions(4);
        when(routineService.addExercise(anyLong(), any(Exercise_RoutineRequestDto.class))).thenReturn(new RoutineResponseDto());
        mockMvc.perform(post("/api/v1/routines/exercises/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(exerciseRequest)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void updateExercise_shouldReturnOk() throws Exception {
        Exercise_RoutineRequestDto exerciseRequest = new Exercise_RoutineRequestDto();
        exerciseRequest.setExerciseId(1L);
        exerciseRequest.setSeries(2);
        exerciseRequest.setQuantity(3);
        exerciseRequest.setRepetitions(4);
        when(routineService.updateExercise(anyLong(), any(Exercise_RoutineRequestDto.class))).thenReturn(new RoutineResponseDto());
        mockMvc.perform(put("/api/v1/routines/exercises/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(exerciseRequest)))
                .andExpect(status().isOk());
    }
}







