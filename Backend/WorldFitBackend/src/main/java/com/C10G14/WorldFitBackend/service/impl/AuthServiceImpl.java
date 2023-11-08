package com.C10G14.WorldFitBackend.service.impl;

import com.C10G14.WorldFitBackend.exception.AlreadyExistException;
import com.C10G14.WorldFitBackend.mapper.AuthDtoMapper;
import com.C10G14.WorldFitBackend.service.AuthService;
import com.C10G14.WorldFitBackend.service.EmailService;
import com.C10G14.WorldFitBackend.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.C10G14.WorldFitBackend.dto.user.AuthenticationRequestDto;
import com.C10G14.WorldFitBackend.dto.user.AuthenticationResponseDto;
import com.C10G14.WorldFitBackend.dto.user.RegisterRequestDto;
import com.C10G14.WorldFitBackend.entity.User;
import com.C10G14.WorldFitBackend.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.C10G14.WorldFitBackend.security.jwt.JwtService;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AuthDtoMapper mapper;
    private final ImageService imageService;
    private final EmailService emailService;

    @Override
    public AuthenticationResponseDto register(RegisterRequestDto request) {
        if (userRepository.existsByEmail(request.getEmail().toLowerCase())){
            throw new AlreadyExistException("Error: Email already taken");
        }
        User newUser = mapper.requestToEntity(request);

        if (!Objects.equals(request.getProfileImg(),null)){
        if (imageService.isImageValid(request.getProfileImg())){
            newUser.setProfileImg(imageService.upload(
                    request.getProfileImg(),request.getEmail()));
        }
        }

        userRepository.save(newUser);
        emailService.sendHtmlEmail(newUser,"Bienvenido");
        String jwtToken = jwtService.generateToken(newUser);
        AuthenticationResponseDto authResponse = new AuthenticationResponseDto();
        authResponse.setToken(jwtToken);
        return authResponse ;
    }

    @Override
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Error: Username not found"));
        String jwtToken = jwtService.generateToken(user);
        AuthenticationResponseDto authResponse = new AuthenticationResponseDto();
        authResponse.setToken(jwtToken);
        return authResponse;
    }
}
