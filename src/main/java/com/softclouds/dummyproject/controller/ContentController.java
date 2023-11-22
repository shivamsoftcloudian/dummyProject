package com.softclouds.dummyproject.controller;

import com.softclouds.dummyproject.Request.ContentRequest;
import com.softclouds.dummyproject.Service.ContentService;
import com.softclouds.dummyproject.base.BaseController;
import com.softclouds.dummyproject.base.response.Response;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category/{categoryId}/sub-category/{subCategoryId}/content")
public class ContentController extends BaseController {

    private final ContentService contentService;
    private static final Logger logger = LoggerFactory.getLogger(ContentController.class);


    @PostMapping()
    public ResponseEntity<Response> createContent(@RequestBody ContentRequest request, @PathVariable Long subCategoryId) {
//        VendorDTO vendor = vendorService.addVendor(request);
        return data(contentService.createContent(request, subCategoryId));
    }

    @GetMapping("/{contentId}")
    public ResponseEntity<Response> getContentById(@PathVariable Long contentId) {

//        VendorDTO vendor = vendorService.getVendor(vendorId);
        return data(contentService.getContentById(contentId));
    }

    @GetMapping("/all")
    public ResponseEntity<Response> getAllContentBySubCategory(@PathVariable Long subCategoryId) {

        return data(contentService.getAllContentBySubCategory(subCategoryId));
    }

}
