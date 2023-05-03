package com.elearningweb.library.dto;

import com.elearningweb.library.model.Role;
import jakarta.annotation.Nullable;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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
}
