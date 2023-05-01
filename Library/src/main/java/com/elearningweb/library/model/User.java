package com.elearningweb.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;

    private String lastName;

    private String username;

    @JsonIgnore
    private String password;
    @OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Role> roles;
}
