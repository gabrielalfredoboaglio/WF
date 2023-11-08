package com.C10G14.WorldFitBackend.dto.user;

import com.C10G14.WorldFitBackend.dto.routine.RoutineResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String clientSince;
    private String name;
    private List<String> roles;
    private String profileImg;
    private Double weight;
    private Double height;
    private String sex;
    private Integer age;
    private String objective;
    private String medical_indication;
    private List<RoutineResponseDto> routines;
}
