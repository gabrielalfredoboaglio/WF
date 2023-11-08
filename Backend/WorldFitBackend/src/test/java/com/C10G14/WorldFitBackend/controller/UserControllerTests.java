package com.C10G14.WorldFitBackend.controller;

import com.C10G14.WorldFitBackend.dto.user.RegisterRequestDto;
import com.C10G14.WorldFitBackend.dto.user.UserDto;
import com.C10G14.WorldFitBackend.entity.User;
import com.C10G14.WorldFitBackend.enumeration.ESex;
import com.C10G14.WorldFitBackend.security.jwt.JwtService;
import com.C10G14.WorldFitBackend.service.UserService;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class UserControllerTests{

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private JwtService jwtService;
    @Autowired
    ObjectMapper mapper;
    private List<UserDto> users;
    private User user1;
    private User user2;
    private UserDto userDto1;
    private UserDto userDto2;

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


        user2 = User.builder()
                .email("user2@example.com")
                .password("password")
                .sex(ESex.FEMALE)
                .age(25)
                .weight(65.0)
                .height(1.55)
                .profileImg("https://avatars.githubusercontent.com/u/83373185?v=4")
                .build();

        userDto1 = UserDto.builder()
                .email("user1@example.com")
                .sex("male")
                .age(20)
                .weight(62.0)
                .height(1.60)
                .profileImg("https://avatars.githubusercontent.com/u/83373185?v=4")
                .build();

        userDto2 = UserDto.builder()
                .email("user2@example.com")
                .sex("male")
                .age(25)
                .weight(65.0)
                .height(1.55)
                .profileImg("https://avatars.githubusercontent.com/u/83373185?v=4")
                .build();

        users = Arrays.asList(userDto1, userDto2);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void getAllUsers_ReturnsListOfUsers() throws Exception {
        given(userService.getAllUsers()).willReturn(users);

        ResultActions response = mockMvc.perform(get("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email", Matchers.is("user1@example.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].email", Matchers.is("user2@example.com")));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void getUserById_ReturnsOk() throws Exception {
        given(userService.getUserById(user1.getId())).willReturn(userDto1);

        ResultActions response = mockMvc.perform(get("/api/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(userDto1)));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void createUser_ReturnsUser() throws Exception {
        when(userService.createUser(any(UserDto.class))).thenReturn(userDto1);

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(userDto1)))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.email", is(userDto1.getEmail())))
                .andExpect(jsonPath("$.sex", is(userDto1.getSex())))
                .andExpect(jsonPath("$.age", is(userDto1.getAge())))
                .andExpect(jsonPath("$.weight", is(userDto1.getWeight())))
                .andExpect(jsonPath("$.height", is(userDto1.getHeight())))
                .andExpect(jsonPath("$.profileImg", is(userDto1.getProfileImg())));

        verify(userService).createUser(any(UserDto.class));
    }

    @Test
    public void updateRole_ReturnsOk() throws Exception {
        when(userService.updateRole(eq(1L), eq("admin"))).thenReturn(userDto1);

        mockMvc.perform(put("/api/v1/users/role/1/admin")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService).updateRole(eq(1L), eq("admin"));
    }

    @Test
    public void deleteUser_returnsNothing() throws Exception {
        mockMvc.perform(delete("/api/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(userService).deleteUser(eq(1L));
    }
}


