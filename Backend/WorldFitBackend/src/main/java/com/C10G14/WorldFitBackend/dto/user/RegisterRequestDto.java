package com.C10G14.WorldFitBackend.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequestDto {
    @NotBlank(message = "Email is required")
    @Email (regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$",
            message = "Email direction isn't valid")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^([a-zA-Z0-9,ñ]){8,20}$",
            message = "Password must contain at least 8 characters including letters, numbers, spaces and commas")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^([a-zA-Zñ ]){1,20}$",
            message = "Name must contain only letters and spaces and be maximum 12 characters")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
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
}
