package com.softclouds.dummyproject.controller;

import com.softclouds.dummyproject.Request.CategoryRequest;
import com.softclouds.dummyproject.Service.CategoryService;
import com.softclouds.dummyproject.base.BaseController;
import com.softclouds.dummyproject.base.response.Response;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController extends BaseController {

    private final CategoryService categoryService;
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);


    @PostMapping()
    public ResponseEntity<Response> createCategory(@RequestBody CategoryRequest request) {
//        VendorDTO vendor = vendorService.addVendor(request);
        return data(categoryService.createCategory(request));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Response> getCategoryById(@PathVariable Long categoryId) {

//        VendorDTO vendor = vendorService.getVendor(vendorId);
        return data(categoryService.getCategoryById(categoryId));
    }

    @GetMapping("/all")
    public ResponseEntity<Response> getAllCategories() {

        return data(categoryService.getAllCategories());
    }
}
