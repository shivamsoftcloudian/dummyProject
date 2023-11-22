package com.softclouds.dummyproject.Service;

import com.softclouds.dummyproject.Request.CategoryRequest;
import com.softclouds.dummyproject.model.SubCategory;

import java.util.List;

public interface SubCategoryService {

    SubCategory createSubCategory(CategoryRequest request, Long category_id);

    SubCategory getSubCategoryById(Long subCategory_id);

    List<SubCategory> getAllSubCategories();

    List<SubCategory> getAllSubCategoriesByCategory(Long category_id);
}
