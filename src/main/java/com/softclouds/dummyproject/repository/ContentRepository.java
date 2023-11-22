package com.softclouds.dummyproject.repository;

import com.softclouds.dummyproject.model.Content;
import com.softclouds.dummyproject.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {

    List<Content> findAllBySubCategory(SubCategory subCategory);
}
