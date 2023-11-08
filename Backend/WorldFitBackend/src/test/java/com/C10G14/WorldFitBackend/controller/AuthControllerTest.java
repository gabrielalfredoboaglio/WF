package com.C10G14.WorldFitBackend.controller;

import com.C10G14.WorldFitBackend.dto.user.AuthenticationRequestDto;
import com.C10G14.WorldFitBackend.dto.user.AuthenticationResponseDto;
import com.C10G14.WorldFitBackend.dto.user.RegisterRequestDto;
import com.C10G14.WorldFitBackend.security.jwt.JwtAuthenticationFilter;
import com.C10G14.WorldFitBackend.security.jwt.JwtService;
import com.C10G14.WorldFitBackend.service.impl.AuthServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AuthServiceImpl authService;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private JwtService jwtService;
    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    private RegisterRequestDto registerRequestDto;
    private AuthenticationRequestDto authenticationRequestDto;
    private AuthenticationResponseDto authenticationResponseDto;

    @BeforeEach
    public void init() {
        registerRequestDto = RegisterRequestDto.builder()
                .email("test@test.com")
                .name("TestUser")
                .password("passwordTest")
                .sex("male")
                .age(15)
                .height(80.0)
                .weight(1.75)
                //.profileImg("https://avatars.githubusercontent.com/u/83373185?v=4")
                .build();

        authenticationRequestDto = AuthenticationRequestDto.builder()
                .email("test@test.com")
                .password("passwordTest")
                .build();

        authenticationResponseDto = AuthenticationResponseDto.builder().token("tokenTest").build();
    }

    @Test
    public void registerANewUserReturnsOk() throws Exception {
        // given
        given(authService.register(registerRequestDto)).willReturn(authenticationResponseDto);
        // when
        ResultActions result = mockMvc.perform(post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequestDto)));
        // then
        result.andExpect(status().isOk());
    }

    @Test
    public void authenticateAExistingUserReturnsOk() throws Exception {

        given(authService.authenticate(authenticationRequestDto)).willReturn(authenticationResponseDto);

        ResultActions result = mockMvc.perform(post("/api/v1/auth/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authenticationRequestDto)));

        result.andExpect(status().isOk());
    }
}


