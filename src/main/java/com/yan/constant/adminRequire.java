package com.yan.constant;

import java.lang.annotation.*;

/**
 * Package ：com.yan.constant
 * Description：
 * date： 2019/1/25 上午9:41
 * author： Li KaiYan
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface adminRequire {

    boolean adminRequire() default true;

}
