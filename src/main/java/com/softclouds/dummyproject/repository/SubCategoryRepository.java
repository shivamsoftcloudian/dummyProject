package com.softclouds.dummyproject.repository;

import com.softclouds.dummyproject.model.Category;
import com.softclouds.dummyproject.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

    List<SubCategory> findAllByCategory(Category category);
}
