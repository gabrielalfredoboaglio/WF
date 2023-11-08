package com.C10G14.WorldFitBackend.service.impl;

import com.C10G14.WorldFitBackend.dto.user.EditUserDto;
import com.C10G14.WorldFitBackend.dto.user.SimpleUserDto;
import com.C10G14.WorldFitBackend.dto.user.UserDto;
import com.C10G14.WorldFitBackend.entity.Role;
import com.C10G14.WorldFitBackend.entity.User;
import com.C10G14.WorldFitBackend.enumeration.ERole;
import com.C10G14.WorldFitBackend.enumeration.ESex;
import com.C10G14.WorldFitBackend.exception.ForbiddenException;
import com.C10G14.WorldFitBackend.exception.InputNotValidException;
import com.C10G14.WorldFitBackend.exception.NotFoundException;
import com.C10G14.WorldFitBackend.mapper.UserDtoMapper;
import com.C10G14.WorldFitBackend.repository.RoleRepository;
import com.C10G14.WorldFitBackend.repository.UserRepository;
import com.C10G14.WorldFitBackend.service.ImageService;
import com.C10G14.WorldFitBackend.service.UserService;
import com.C10G14.WorldFitBackend.util.DtoFormatter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ImageService imageService;
    private final UserDtoMapper mapper;
    private final DtoFormatter formatter;

    @Transactional
    @Override
    public List<UserDto> getAllUsers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        List<UserDto> users = new ArrayList<>();
        if (roles.contains("ROLE_ADMIN")) {
            users.addAll(mapper.usersToDtoList(userRepository.findAll()));
        } else if (roles.contains("ROLE_COACH")) {
            users.addAll(this.getByRole("user"));
            users.addAll(this.getByRole("customer"));
        }
        return users;
    }

    @Transactional
    @Override
    public UserDto getUserById(Long id) {
        return mapper.entityToDto(userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Error: user not found")));
    }

    @Transactional
    @Override
    public SimpleUserDto getSimpleUserById(Long id) {
        return mapper.entityToSimpleDto(userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Error: user not found")));
    }

    @Transactional
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = mapper.dtoToEntity(userDto);
        userRepository.save(user);
        return mapper.entityToDto(userRepository.findByEmail(userDto.getEmail()).orElseThrow( () ->
                new NotFoundException("There is a problem creating the user")));
    }

    @Transactional
    @Override
    public UserDto updateUser(Long id, EditUserDto userDto){
        User user = userRepository.findById(id).orElseThrow(()->
                new NotFoundException("Error: user not found"));

        userDto.getName().ifPresent( e ->
                user.setName(formatter.formatName(e)));

        userDto.getProfileImg().ifPresent( e ->{
            if (imageService.isImageValid(e)){
                imageService.upload(e,user.getEmail());}
        });

        userDto.getSex().ifPresent( e -> {
            switch (e.toLowerCase()) {
                case "male" -> user.setSex(ESex.MALE);
                case "female" -> user.setSex(ESex.FEMALE);
                default -> throw new InputNotValidException(
                        "Invalid gender, must be male or female");
            }
        });

        userDto.getEmail().ifPresent(user::setEmail);
        userDto.getAge().ifPresent(user::setAge);
        userDto.getWeight().ifPresent(user::setWeight);
        userDto.getHeight().ifPresent(user::setHeight);
        userDto.getObjective().ifPresent(user::setObjective);
        userDto.getMedicalIndication().ifPresent(user::setMedical_indication);

        userRepository.save(user);
        return mapper.entityToDto(user);
    }
    @Transactional
    @Override
    public UserDto updateRole(Long id, String requestRole) {
        User user = userRepository.findById(id).
                orElseThrow(()-> new NotFoundException("Error: User not found"));
        if (user.getRole().contains(roleRepository.findByName(ERole.ROLE_ADMIN).get()))
            throw new ForbiddenException("Error: Cant change admin role");

        Role role = roleRepository.findByName(Role.RoletoERole(requestRole))
                .orElseThrow(() -> new NotFoundException("Error: Role not found."));
        user.getRole().clear();
        user.getRole().add(role);
        userRepository.save(user);
        return mapper.entityToDto(user);
    }

    @Transactional
    @Override
    public List<UserDto> getByRole(String requestRole) {
        Role role = roleRepository.findByName(Role.RoletoERole(requestRole))
                .orElseThrow(() -> new NotFoundException("Error: Role not found."));
        return mapper.usersToDtoList(userRepository.findByRole(role).orElseThrow(() ->
                new NotFoundException("Error retrieving role " + requestRole)));
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
