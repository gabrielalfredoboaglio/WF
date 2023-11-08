package com.C10G14.WorldFitBackend.dto.user;

import com.C10G14.WorldFitBackend.dto.routine.RoutineResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SimpleUserDto {
    private String id;
    private String email;
    private String name;
    private String clientSince;
    private String profileImg;
    private List<RoutineResponseDto> routines;
}
