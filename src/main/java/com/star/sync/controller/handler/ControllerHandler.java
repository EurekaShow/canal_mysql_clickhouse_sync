package com.star.sync.controller.handler;

import com.star.sync.model.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @author eureka
 * @version 1.0
 * @since 2020-08-14
 */
@ControllerAdvice
public class ControllerHandler {
    private static final Logger logger = LoggerFactory.getLogger(ControllerHandler.class);

    @ExceptionHandler
    @ResponseBody
    public Object exceptionHandler(Exception e, HttpServletResponse response) {
        logger.error("unknown_error", e);
        return new Response<>(2, e.getMessage(), null).toString();
    }
}
