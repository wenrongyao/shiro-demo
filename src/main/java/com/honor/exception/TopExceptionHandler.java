package com.honor.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 统一处理异常
 * <p>
 * Created by rongyaowen
 * on 2018/12/17.
 */
@ControllerAdvice
public class TopExceptionHandler {

    /**
     * 未授权异常==>统一跳转东到未授权页面
     *
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
    public String handleException(Exception e) {
        return "unauthorized";
    }
}