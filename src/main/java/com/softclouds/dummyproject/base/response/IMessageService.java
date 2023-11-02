package com.softclouds.dummyproject.base.response;

public interface IMessageService {

    /**
     * This method is responsible for get the message bundle using provided code.
     *
     * @param responseCode
     * @param params
     * @return
     */
    String getMessage(final ResponseCode responseCode, final String... params);
}
