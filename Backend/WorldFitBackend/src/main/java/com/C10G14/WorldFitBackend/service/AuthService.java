package com.C10G14.WorldFitBackend.service;

import com.C10G14.WorldFitBackend.dto.user.AuthenticationRequestDto;
import com.C10G14.WorldFitBackend.dto.user.AuthenticationResponseDto;
import com.C10G14.WorldFitBackend.dto.user.RegisterRequestDto;

import java.io.IOException;

public interface AuthService {
    AuthenticationResponseDto register (RegisterRequestDto request) throws IOException;
    AuthenticationResponseDto authenticate(AuthenticationRequestDto request);
}
