package com.elearningweb.library.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private long id;
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<User> users;

    public Role(String name) {
        this.name = name;
    }
}
