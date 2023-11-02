package com.softclouds.dummyproject.base.response;

import com.google.common.collect.Lists;
import com.softclouds.dummyproject.base.exceptionHandling.ApplicationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResponseServiceImpl implements ResponseService {

    private final IMessageService messageService;

    private final HttpServletRequest httpServletRequest;

    private ResponseEntity<Response> error(HttpStatus httpStatus, ResponseCode responseCode, List<String> errors) {
        return new ResponseEntity<>(
                Response.builder()
                        .status(httpStatus.value())
                        .date(new Date())
                        .responseCode(responseCode)
                        .errors(errors)
                        .path(httpServletRequest.getRequestURI())
                        .build(), HttpStatus.OK);
    }

    /**
     * {@inheritDoc}
     *
     * @param responseCode
     * @param args
     * @return
     */
    @Override
    public ResponseEntity<Response> success(ResponseCode responseCode, String... args) {

        final String message = messageService.getMessage(responseCode, args);

        return new ResponseEntity<>(
                Response.builder()
                        .status(HttpStatus.OK.value())
                        .responseCode(responseCode)
                        .data(message)
                        .path(httpServletRequest.getRequestURI()).build(), HttpStatus.OK);
    }

    /**
     * {@inheritDoc}
     *
     * @param responseCode
     * @param args
     * @return
     */
    @Override
    public ResponseEntity<Response> error(ResponseCode responseCode, String... args) {

        final String message = messageService.getMessage(responseCode, args);

        if (responseCode == ResponseCode.NOT_FOUND) {
            return error(HttpStatus.NOT_FOUND, responseCode, Lists.newArrayList(message));
        } else if (responseCode == ResponseCode.ACCESS_DENIED) {
            return error(HttpStatus.FORBIDDEN, responseCode, Lists.newArrayList(message));
        } else {
            return error(HttpStatus.BAD_REQUEST, responseCode, Lists.newArrayList(message));
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param errors
     * @return
     */
    @Override
    public ResponseEntity<Response> error(List<String> errors) {
        return error(HttpStatus.BAD_REQUEST, ResponseCode.BAD_REQUEST, errors);
    }

    /**
     * {@inheritDoc}
     *
     * @param applicationException
     * @return
     */
    @Override
    public ResponseEntity<Response> error(ApplicationException applicationException) {
        return error(HttpStatus.NOT_FOUND, applicationException.getResponseCode(),
                Lists.newArrayList(applicationException.getMessage()));
//        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param entity
     * @return
     */
    @Override
    public ResponseEntity<Response> data(Object entity) {

        return new ResponseEntity<>(
                Response.builder()
                        .status(HttpStatus.OK.value())
                        .data(new Date())
                        .responseCode(ResponseCode.ENTITY)
                        .data(entity)
                        .path(httpServletRequest.getRequestURI()).build(), HttpStatus.OK);
    }

    /**
     * {@inheritDoc}
     *
     * @param page
     * @return
     */
    @Override
    public ResponseEntity<Response> data(Page<?> page) {
        return new ResponseEntity<>(Response.builder().status(HttpStatus.OK.value())
                .responseCode(ResponseCode.ENTITY)
                .path(httpServletRequest.getRequestURI()).build(), HttpStatus.OK);
    }
}
