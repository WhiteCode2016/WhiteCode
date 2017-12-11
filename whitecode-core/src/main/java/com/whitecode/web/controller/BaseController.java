package com.whitecode.web.controller;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

/**
 * Controller基类
 * Created by White on 2017/12/11.
 */
public class BaseController implements MessageSourceAware {

    private MessageSource messageSource;

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }
}
