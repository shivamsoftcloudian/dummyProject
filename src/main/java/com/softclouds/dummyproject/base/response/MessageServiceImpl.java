package com.softclouds.dummyproject.base.response;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements IMessageService {

    private final MessageSource messageSource;

    /**
     * {@inheritDoc}
     *
     * @param responseCode
     * @param args
     * @return
     */
    @Override
    public String getMessage(ResponseCode responseCode, String[] args) {
        return this.messageSource.getMessage(responseCode.name(), args, Locale.getDefault());
    }
}
