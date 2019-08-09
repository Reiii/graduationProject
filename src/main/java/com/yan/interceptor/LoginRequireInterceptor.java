package com.yan.interceptor;

import com.yan.constant.loginRequire;
import com.yan.exception.NoLoginException;
import com.yan.exception.UserException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * Package ：com.yan.interceptor
 * Description：
 * date： 2019/1/25 上午10:12
 * author： Li KaiYan
 */

@Aspect
@Component
public class LoginRequireInterceptor {

    @Pointcut("execution(* com.yan.controller..*.*(..))")
    public void method(){}

    @Before("method()")
    public void beforeMethod(JoinPoint point) throws NoLoginException {
        Method method = ((MethodSignature)point.getSignature()).getMethod();
        if(method.isAnnotationPresent(loginRequire.class)){
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            HttpServletRequest request = sra.getRequest();
            HttpSession session = request.getSession();
            if(session.getAttribute("user") == null){
                throw new NoLoginException(NoLoginException.NO_LOGIN);
            }
        }
    }
}
