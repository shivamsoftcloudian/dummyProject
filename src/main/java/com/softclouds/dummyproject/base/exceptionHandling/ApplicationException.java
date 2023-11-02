package com.softclouds.dummyproject.base.exceptionHandling;

import com.softclouds.dummyproject.base.response.ResponseCode;
import lombok.Getter;

import java.io.Serial;

@Getter
public class ApplicationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 7365547609387611425L;

    private final ResponseCode responseCode;

    private String[] fields;

    private Throwable exception;

    public ApplicationException(final ResponseCode responseCode, final String message, final String... fields) {
        super(message);
        this.responseCode = responseCode;
        if (fields.length == 0) {
            this.fields = new String[]{message};
        } else {
            this.fields = fields;
        }
    }

    public ApplicationException(Throwable exception) {
        super(exception.getLocalizedMessage());
        this.responseCode = ResponseCode.INTERNAL_ERROR;
        this.exception = exception;
    }
}
