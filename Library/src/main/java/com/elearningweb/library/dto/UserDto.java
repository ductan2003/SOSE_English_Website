package com.elearningweb.library.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private String passwordConfirmation;
}
