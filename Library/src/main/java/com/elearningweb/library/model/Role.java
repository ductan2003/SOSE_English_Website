package com.elearningweb.library.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Setter
@Getter
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private long id;

    Role() {}

    public Role(String name) {
        this.name = name;
    }

    private String name;
}
