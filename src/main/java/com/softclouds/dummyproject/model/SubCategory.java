package com.softclouds.dummyproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subCategory_name;

    private String subCategory_label;

    private String subCategory_desc;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_category")
    private Category category;

    @OneToMany(mappedBy = "subCategory",
            fetch = FetchType.LAZY)
    private Set<Content> contentList = new HashSet<Content>();
}
