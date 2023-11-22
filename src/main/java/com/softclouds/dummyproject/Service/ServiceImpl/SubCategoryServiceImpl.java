package com.softclouds.dummyproject.Service.ServiceImpl;

import com.softclouds.dummyproject.Request.CategoryRequest;
import com.softclouds.dummyproject.Service.SubCategoryService;
import com.softclouds.dummyproject.base.exceptionHandling.ResourceNotFoundException;
import com.softclouds.dummyproject.model.Category;
import com.softclouds.dummyproject.model.SubCategory;
import com.softclouds.dummyproject.repository.CategoryRepository;
import com.softclouds.dummyproject.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private static final Logger logger = LoggerFactory.getLogger(SubCategoryServiceImpl.class);

    @Override
    public SubCategory createSubCategory(CategoryRequest request, Long category_id) {

        Category category = categoryRepository.findById(category_id).orElseThrow(() ->
                new ResourceNotFoundException("Category Not Found with ID: " + category_id));

        SubCategory subCategory = new SubCategory();
        subCategory.setSubCategory_name(request.getCategory_name());
        subCategory.setSubCategory_label(request.getCategory_label());
        subCategory.setSubCategory_desc(request.getCategory_desc());
        subCategory.setCategory(category);
        subCategory = subCategoryRepository.save(subCategory);
        logger.info("New Sub-Category is Added :" + subCategory);
        return subCategory;
    }

    @Override
    public SubCategory getSubCategoryById(Long subCategory_id) {
        logger.info("SubCategory is Requested with id :" + subCategory_id);
        SubCategory subCategory = subCategoryRepository.findById(subCategory_id).orElseThrow(() ->
                new ResourceNotFoundException("Category Not Found with ID: " + subCategory_id));
        return subCategory;
    }

    @Override
    public List<SubCategory> getAllSubCategories() {
        List<SubCategory> subCategoryList = new ArrayList<SubCategory>();
        logger.info("All Sub-Categories are requested");
        subCategoryList = subCategoryRepository.findAll();
        if (subCategoryList.isEmpty()) {
            logger.error("No Sub-Categories Found ");
            throw new ResourceNotFoundException("No Sub-Categories Found");
        }
        return subCategoryList;
    }

    @Override
    public List<SubCategory> getAllSubCategoriesByCategory(Long category_id) {

        Category category = categoryRepository.findById(category_id).orElseThrow(() ->
                new ResourceNotFoundException("Category Not Found with ID: " + category_id));

        List<SubCategory> subCategoryList = new ArrayList<SubCategory>();

        logger.info("All Sub-Categories are requested");
        subCategoryList = subCategoryRepository.findAllByCategory(category);
        if (subCategoryList.isEmpty()) {
            logger.error("No Sub-Categories Found in category with id : " + category_id);
            throw new ResourceNotFoundException("No Sub-Categories Found in category with id : " + category_id);
        }
        return subCategoryList;
    }

    //TODO: update and Delete
}
