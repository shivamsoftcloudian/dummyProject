package com.softclouds.dummyproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category_name;

    private String category_label;

    private String category_desc;

    @OneToMany(mappedBy = "category",
            fetch = FetchType.LAZY)
    private Set<SubCategory> sub_category = new HashSet<SubCategory>();

}
