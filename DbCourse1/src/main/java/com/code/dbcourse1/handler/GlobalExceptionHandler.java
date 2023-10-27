package com.code.dbcourse1.handler;

import com.code.dbcourse1.pojo.ResponseException;
import com.code.dbcourse1.pojo.ResultData;
import com.code.dbcourse1.pojo.ReturnCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResultData<String> exception(Exception e){
        log.error(" Exception 全局异常 error = {}", e.getMessage(), e);
        return ResultData.fail(ReturnCode.FAIL.getCode(), e.getMessage());
    }

    @ExceptionHandler(ResponseException.class)
    public ResultData<Object> responseException(ResponseException e){
        log.error(" ResponseException 返回异常值 error = {}", e.getResultData().toString());
        return e.getResultData();
    }

}
