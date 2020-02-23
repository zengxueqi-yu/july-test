package com.july.test.controller;

import com.july.test.config.Result;
import com.july.test.util.BnException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.xml.bind.ValidationException;

/**
 * @author zqk
 * @since 2020/2/7
 */
@RestControllerAdvice
public class AdviceController {

    private static final Logger log = LoggerFactory.getLogger(AdviceController.class);
    private static final String UNIFIED_FAIL_MSG = "服务器内部错误";

    public AdviceController() {}

    @ExceptionHandler({BnException.class})
    public Result bnException(BnException be) {
        log.error(be.toString());
        be.printStackTrace();
        return Result.error(this.errorMsg(be.getMessage()));
    }

    @ExceptionHandler({Exception.class})
    public Result exception(Exception be) {
        log.error("{}", be.getMessage());
        be.printStackTrace();
        return Result.error(this.errorMsg(be.getMessage()));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Result methodArgument(MethodArgumentNotValidException be) {
        BindingResult br = be.getBindingResult();
        FieldError error = br.getFieldError();
        StringBuffer sb = new StringBuffer("校验异常:");
        sb.append(error.getField()).append(error.getDefaultMessage());
        log.error(sb.toString());
        be.printStackTrace();
        return Result.error(this.errorMsg(be.getMessage()));
    }

    @ExceptionHandler({BindException.class})
    public Result bindException(BindException be) {
        FieldError error = be.getFieldError();
        StringBuffer sb = new StringBuffer("参数绑定异常:");
        sb.append(error.getField()).append(error.getDefaultMessage());
        log.error(sb.toString());
        be.printStackTrace();
        return Result.error(this.errorMsg(be.getMessage()));
    }

    @ExceptionHandler({ValidationException.class})
    public Result constraintViolation(ValidationException be) {
        log.error(be.getMessage());
        be.printStackTrace();
        return Result.error(this.errorMsg(be.getMessage()));
    }

    @ExceptionHandler({HttpMessageConversionException.class})
    public Result parameterTypeConversionException(HttpMessageConversionException be) {
        log.error(be.getMessage());
        be.printStackTrace();
        return Result.error(this.errorMsg(be.getMessage()));
    }

    private String errorMsg(String message) {
        return StringUtils.isEmpty(message) ? "服务器内部错误" : message;
    }
}
