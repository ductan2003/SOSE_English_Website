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
    private Long id;
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
    private String profileImage;

    public UserDto(String firstName, String lastName, String username, String password, String confirmPassword) {            this.firstName = firstName;
            this.lastName = lastName;
            this.username = username;
            this.password = password;
            this.confirmPassword = confirmPassword;
        }

    public UserDto(String firstName, String lastName, String username, String password) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.username = username;
            this.password = password;
        }
    }

