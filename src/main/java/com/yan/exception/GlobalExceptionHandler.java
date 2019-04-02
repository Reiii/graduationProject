package com.yan.exception;

import com.yan.util.Status;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @ExceptionHandler(value = NoLoginException.class)
    public ErrorInfo<NoLoginException> handleNoLoginException(HttpServletResponse response, NoLoginException ex){
        ErrorInfo<NoLoginException> info = new ErrorInfo<>();
        info.setCode(ErrorInfo.ERROR);
        info.setMsg(ex.getMessage());
        return info;

    }

    @ExceptionHandler(value = ActivityException.class)
    public ErrorInfo<UserException> userExceptionErrorInfo(HttpServletRequest request, ActivityException ex){
        ErrorInfo<UserException> info = new ErrorInfo<>();
        info.setCode(ErrorInfo.ERROR);
        info.setMsg(ex.getMessage());
        return info;
    }

    @ExceptionHandler(value = ForumException.class)
    public ErrorInfo<UserException> forumExceptionErrorInfo(HttpServletRequest request, ForumException ex){
        ErrorInfo<UserException> info = new ErrorInfo<>();
        info.setCode(ErrorInfo.ERROR);
        info.setMsg(ex.getMessage());
        return info;
    }

}
