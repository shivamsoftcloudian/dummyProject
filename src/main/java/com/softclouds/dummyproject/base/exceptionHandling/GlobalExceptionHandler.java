package com.softclouds.dummyproject.base.exceptionHandling;

import com.softclouds.dummyproject.base.BaseController;
import com.softclouds.dummyproject.base.response.ResponseCode;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends BaseController {

//    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        return error(ResponseCode.NOT_FOUND, exception.getLocalizedMessage());
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<?> experienceException(ApplicationException exception, WebRequest request) {
        return error(exception.getResponseCode(), exception.getFields());
    }
}