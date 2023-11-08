package com.C10G14.WorldFitBackend.mapper;

import com.C10G14.WorldFitBackend.dto.routine.RoutineResponseDto;
import com.C10G14.WorldFitBackend.dto.user.SimpleUserDto;
import com.C10G14.WorldFitBackend.dto.user.UserDto;
import com.C10G14.WorldFitBackend.entity.User;
import com.C10G14.WorldFitBackend.enumeration.ERole;
import com.C10G14.WorldFitBackend.enumeration.ESex;
import com.C10G14.WorldFitBackend.exception.NotFoundException;
import com.C10G14.WorldFitBackend.repository.RoleRepository;
import com.C10G14.WorldFitBackend.util.DtoFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class UserDtoMapper {
    private final RoleRepository roleRepository;
    private final RoutineDtoMapper routineMapper;
    private final DtoFormatter formatter;

    public UserDto entityToDto (User user){

        List <RoutineResponseDto> routines = user.getRoutines().stream().map(
                routineMapper::EntityToDto
        ).toList();

        List <String> strRoles = user.getRole().stream().map(
                (r)-> r.getName().name()).toList();

        return new UserDto(user.getId(),
                user.getEmail(),
                user.getClientSince(),
                user.getName(),
                strRoles,
                user.getProfileImg(),
                user.getWeight(),
                user.getHeight(),
                user.getSex().name(),
                user.getAge(),
                user.getObjective(),
                user.getMedical_indication(),
                routines);
    }

    public SimpleUserDto entityToSimpleDto (User user){
        List <RoutineResponseDto> routines = user.getRoutines().stream().map(
                routineMapper::EntityToDto
        ).toList();

        return new SimpleUserDto(user.getId().toString(),
                user.getEmail(),
                user.getClientSince(),
                user.getName(),
                user.getProfileImg(),
                routines);
    }

    public User dtoToEntity (UserDto dto){
        ESex sex = (Objects.equals(dto.getSex(),null)) ? ESex.NOT_SPECIFIED :
                (dto.getSex().equalsIgnoreCase("male"))?  ESex.MALE : ESex.FEMALE ;
        User user = new User();
        user.setEmail(dto.getEmail().toLowerCase());
        user.setName(formatter.formatName(dto.getName()));
        user.setAge(dto.getAge());
        user.setHeight(dto.getHeight());
        user.setWeight(dto.getWeight());
        user.setSex(sex);
        user.setProfileImg(dto.getProfileImg());
        user.setObjective(dto.getObjective());
        user.setMedical_indication(dto.getMedical_indication());

        user.setRole(dto.getRoles().stream().map(
                (r) -> roleRepository.findByName(ERole.valueOf(r.toUpperCase()))
                        .orElseThrow(() -> new NotFoundException("Error: Role not found: " + r))
        ).toList());

        user.setRoutines(new LinkedHashSet<>());

        return user;
    }

    public List<UserDto> usersToDtoList (List<User> users){
        List <UserDto> usersDto = new ArrayList<>();
        for (User user : users) {
            usersDto.add(entityToDto(user));
        }
        return usersDto;
    }
}
