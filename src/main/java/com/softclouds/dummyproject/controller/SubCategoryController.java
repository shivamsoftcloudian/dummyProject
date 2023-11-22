package com.softclouds.dummyproject.controller;

import com.softclouds.dummyproject.Request.CategoryRequest;
import com.softclouds.dummyproject.Service.SubCategoryService;
import com.softclouds.dummyproject.base.BaseController;
import com.softclouds.dummyproject.base.response.Response;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category/{categoryId}/sub-category")
public class SubCategoryController extends BaseController {

    private final SubCategoryService subCategoryService;
    private static final Logger logger = LoggerFactory.getLogger(SubCategoryController.class);


    @PostMapping()
    public ResponseEntity<Response> createSubCategory(@RequestBody CategoryRequest request, @PathVariable Long categoryId) {
//        VendorDTO vendor = vendorService.addVendor(request);
        return data(subCategoryService.createSubCategory(request, categoryId));
    }

    @GetMapping("/{subCategoryId}")
    public ResponseEntity<Response> getSubCategoryById(@PathVariable Long subCategoryId, @PathVariable Long categoryId) {

//        VendorDTO vendor = vendorService.getVendor(vendorId);
        return data(subCategoryService.getSubCategoryById(subCategoryId));
    }

    @GetMapping("/all")
    public ResponseEntity<Response> getAllSubCategoriesByCategory(@PathVariable Long categoryId) {

        return data(subCategoryService.getAllSubCategoriesByCategory(categoryId));
    }
}
