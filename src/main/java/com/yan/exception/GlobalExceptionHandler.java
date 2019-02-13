package com.yan.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Package ：com.yan.exception
 * Description：
 * date： 2019/1/25 上午9:36
 * author： Li KaiYan
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UserException.class)
    @ResponseBody
    public ErrorInfo<UserException> userExceptionErrorInfo(HttpServletRequest request, UserException ex){
        ErrorInfo<UserException> info = new ErrorInfo<>();
        info.setCode(ErrorInfo.ERROR);
        info.setMsg(ex.getMessage());
        return info;
    }

}
