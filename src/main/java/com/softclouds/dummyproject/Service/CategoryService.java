package com.softclouds.dummyproject.Service;

import com.softclouds.dummyproject.Request.CategoryRequest;
import com.softclouds.dummyproject.Request.UserRequest;
import com.softclouds.dummyproject.model.Category;
import com.softclouds.dummyproject.model.User;

import java.util.List;

public interface CategoryService {

    Category createCategory(CategoryRequest request);

    Category getCategoryById(Long category_id);

    List<Category> getAllCategories();
}
