package com.softclouds.dummyproject.base;

import com.softclouds.dummyproject.base.exceptionHandling.ApplicationException;
import com.softclouds.dummyproject.base.response.Response;
import com.softclouds.dummyproject.base.response.ResponseCode;
import com.softclouds.dummyproject.base.response.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class BaseController {

    @Autowired
    private ResponseService responseService;

    /**
     * return success response with provided object
     *
     * @param entity
     * @return
     */
    protected ResponseEntity<Response> data(final Object entity) {
        return this.responseService.data(entity);
    }

    /**
     * return success response with provided object
     *
     * @param code
     * @param fields
     * @return
     */
    protected ResponseEntity<Response> success(final ResponseCode code, final String... fields) {
        return this.responseService.success(code, fields);
    }

    /**
     * return error message with 500 Status code
     * {Use this method when we have unknown exception}
     *
     * @param ex
     * @return
     */
    protected ResponseEntity<Response> error(Throwable ex) {
        return this.responseService.error(new ApplicationException(ex));
    }

    /**
     * return error message with 400 Status Code
     * { Use this method when we have known exception or
     * if we want to throw BadRequestException}
     *
     * @param code
     * @param fields
     * @return
     */
    protected ResponseEntity<Response> error(final ResponseCode code, final String... fields) {
        return this.responseService.error(code, fields);
    }

    /**
     * This method will return error messages with 400 status code.
     *
     * @param errors
     * @return
     */
    protected ResponseEntity<Response> error(final List<String> errors) {
        return this.responseService.error(errors);
    }

    protected ResponseEntity<Response> data(final Page<?> page) {
        return this.responseService.data(page);
    }

}
