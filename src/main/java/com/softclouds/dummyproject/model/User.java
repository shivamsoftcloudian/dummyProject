package com.softclouds.dummyproject.model;

import com.softclouds.dummyproject.model.Enum.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String userName;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

}
