package org.example.rest.base.config.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.WebException;
import org.example.rest.base.config.swagger.model.WebResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(basePackages = { "org.example" })
public class GlobalExceptionResolver {
    private static final Logger logger = LogManager.getLogger(GlobalExceptionResolver.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public WebResponse<Object> handleException(Exception e) {
        logger.error(e.getMessage());
        if (e instanceof WebException) {
            return WebResponse.error((WebException) e);
        }
        return WebResponse.error(e);
    }
}
