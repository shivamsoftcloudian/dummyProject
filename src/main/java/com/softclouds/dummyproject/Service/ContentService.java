package com.softclouds.dummyproject.Service;

import com.softclouds.dummyproject.Request.ContentRequest;
import com.softclouds.dummyproject.model.Content;

import java.util.List;

public interface ContentService {

    Content createContent(ContentRequest request, Long subCategory_id);

    Content getContentById(Long content_id);

    List<Content> getAllContent();

    List<Content> getAllContentBySubCategory(Long category_id);
}
