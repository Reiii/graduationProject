package com.yan.constant;

import java.lang.annotation.*;

/**
 * Package ：com.yan.constant
 * Description：
 * date： 2019/1/22 下午4:36
 * author： Li KaiYan
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface loginRequire {

    boolean loginRequire() default true;

}
