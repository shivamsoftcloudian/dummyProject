package com.softclouds.dummyproject.Service.ServiceImpl;

import com.softclouds.dummyproject.Request.CategoryRequest;
import com.softclouds.dummyproject.Service.CategoryService;
import com.softclouds.dummyproject.base.exceptionHandling.ResourceNotFoundException;
import com.softclouds.dummyproject.model.Category;
import com.softclouds.dummyproject.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Override
    public Category createCategory(CategoryRequest request) {
        Category category = new Category();
        category.setCategory_name(request.getCategory_name());
        category.setCategory_label(request.getCategory_label());
        category.setCategory_desc(request.getCategory_desc());
        category = categoryRepository.save(category);
        logger.info("New Category is Added :" + category);
        return category;
    }

    @Override
    public Category getCategoryById(Long category_id) {

       logger.info("Category is Requested with id :" + category_id);
        Category category = categoryRepository.findById(category_id).orElseThrow(() ->
                new ResourceNotFoundException("Category Not Found with ID: " + category_id));
        return category;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<Category>();
        logger.info("All Categories are requested");
        categoryList = categoryRepository.findAll();
        if (categoryList.isEmpty()) {
            logger.error("No Categories Found ");
            throw new ResourceNotFoundException("No Categories Found");
        }
        return categoryList;
    }

    //TODO: update and Delete
}
