package com.C10G14.WorldFitBackend.dto.user;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Optional;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EditUserDto {
    @Email (regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$",
            message = "Email direction isn't valid")
    private String email;

    @Pattern(regexp = "^([a-zA-Z0-9,ñ]){8,20}$",
            message = "Password must contain at least 8 characters including letters, numbers, spaces and commas")
    private String password;

    @Pattern(regexp = "^([a-zA-Zñ ]){1,20}$",
            message = "Name must contain only letters and spaces and be maximum 12 characters")
    private String name;

    private MultipartFile profileImg;

    @DecimalMin(value = "0.00", message = "Weight must be greater than or equal to zero")
    @DecimalMax(value = "999.99", message = "Weight must have two or fewer decimal places")
    private Double weight;

    @DecimalMin(value = "0.00", message = "Height must be greater than or equal to zero")
    @DecimalMax(value = "999.99", message = "Height must have two or fewer decimal places")
    private Double height;

    @Pattern(regexp = "(?i)^(male|female)$",
            message = "Sex must be either male or female (no case sensitive)")
    private String sex;

    @Max(value = 110,
            message = "Maximum age is 110")
    private int age;

    @Pattern(regexp = "^([a-zA-Z0-9ñ ]){10,120}$",
            message = "Objective must be only letters and numbers and 20 characters at must")
    private String objective;

    @Pattern(regexp = "^([a-zA-Z0-9ñ ]){4,120}$",
            message = "Medical indication must be only letters and numbers and 25 characters at must")
    private String medical_indication;

    public Optional<String> getEmail(){
        return Optional.ofNullable(this.email);
    }
    public Optional<MultipartFile> getProfileImg(){
        return Optional.ofNullable(this.profileImg);
    }
    public Optional<Double> getWeight(){
        return Optional.ofNullable(this.weight);
    }
    public Optional<Double> getHeight(){
        return Optional.ofNullable(this.height);
    }
    public Optional<String> getName(){
        return Optional.ofNullable(this.name);
    }
    public Optional<String> getSex(){
        return Optional.ofNullable(this.sex);
    }
    public Optional<Integer> getAge(){
        return Optional.of(this.age);
    }
    public Optional<String> getObjective(){
        return Optional.ofNullable(this.objective);
    }
    public Optional<String> getMedicalIndication(){
        return Optional.ofNullable(this.medical_indication);
    }
}