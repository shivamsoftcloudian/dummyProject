package com.softclouds.dummyproject.Service.ServiceImpl;

import com.softclouds.dummyproject.Request.ContentRequest;
import com.softclouds.dummyproject.Service.ContentService;
import com.softclouds.dummyproject.base.exceptionHandling.ResourceNotFoundException;
import com.softclouds.dummyproject.model.Category;
import com.softclouds.dummyproject.model.Content;
import com.softclouds.dummyproject.model.SubCategory;
import com.softclouds.dummyproject.repository.ContentRepository;
import com.softclouds.dummyproject.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;
    private final SubCategoryRepository subCategoryRepository;
    private static final Logger logger = LoggerFactory.getLogger(ContentServiceImpl.class);

    @Override
    public Content createContent(ContentRequest request, Long subCategory_id) {

        SubCategory subCategory = subCategoryRepository.findById(subCategory_id).orElseThrow(() ->
                new ResourceNotFoundException("Sub-Category Not Found with ID: " + subCategory_id));

        Content content = new Content();
        content.setContent(request.getContent());
        content.setSubCategory(subCategory);
        content = contentRepository.save(content);
        logger.info("New Sub-Category is Added :" + content);
        return content;
    }

    @Override
    public Content getContentById(Long content_id) {
        logger.info("Content is Requested with id :" + content_id);
        Content content = contentRepository.findById(content_id).orElseThrow(() ->
                new ResourceNotFoundException("Content Not Found with ID: " + content_id));
        return content;
    }

    @Override
    public List<Content> getAllContent() {
        List<Content> contentList = new ArrayList<Content>();
        logger.info("All Contents are requested");
        contentList = contentRepository.findAll();
        if (contentList.isEmpty()) {
            logger.error("No Content is Found ");
            throw new ResourceNotFoundException("No Content is Found");
        }
        return contentList;
    }

    @Override
    public List<Content> getAllContentBySubCategory(Long category_id) {

        SubCategory subCategory = subCategoryRepository.findById(category_id).orElseThrow(() ->
                new ResourceNotFoundException("Sub-Category Not Found with ID: " + category_id));

        List<Content> contentList = new ArrayList<Content>();

        logger.info("All Content are requested");
        contentList = contentRepository.findAllBySubCategory(subCategory);
        if (contentList.isEmpty()) {
            logger.error("No Content is Found in sub-category with id : " + category_id);
            throw new ResourceNotFoundException("No Content is Found in sub-category with id : " + category_id);
        }
        return contentList;
    }

    //TODO: update and Delete
}
