package com.elearningweb.library.dto;

import com.elearningweb.library.model.Role;
import jakarta.annotation.Nullable;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @Size(min = 3, max = 10, message = "Invalid first name!(3-10 characters)")
    private String firstName;

    @Size(min = 3, max = 10, message = "Invalid last name!(3-10 characters)")
    private String lastName;

    private String username;

    @Size(min = 3, max = 10, message = "Invalid password!(3-10 characters)")
    private String password;
    private String confirmPassword;
    @Nullable
    private String resetPasswordToken;

    public UserDto(String firstName, String lastName, String username, String password, String confirmPassword) {
    }
}
