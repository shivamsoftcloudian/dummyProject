package com.softclouds.dummyproject.base.response;

import com.softclouds.dummyproject.base.exceptionHandling.ApplicationException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ResponseService {

    /**
     * return Success response
     *
     * @param code
     * @param args
     * @return
     */
    ResponseEntity<Response> success(final ResponseCode code, final String... args);

    /**
     * return error response
     *
     * @param code
     * @param args
     * @return
     */
    ResponseEntity<Response> error(final ResponseCode code, final String... args);

    /**
     * Build and return error response using errors.
     *
     * @param errors
     * @return
     */
    ResponseEntity<Response> error(final List<String> errors);

    /**
     * return error response @ApplicationException
     *
     * @param applicationException
     * @return
     */
    ResponseEntity<Response> error(final ApplicationException applicationException);

    /**
     * return success response with data
     *
     * @param entity
     * @return
     */
    ResponseEntity<Response> data(final Object entity);

    /**
     * return success response with pagination
     *
     * @param page
     * @return
     */
    ResponseEntity<Response> data(final Page<?> page);


}
