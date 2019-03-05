package com.sjsz.app.config.db.annotation;

import java.lang.annotation.*;

/**
 * @Author: qh
 * @Date: 2019/3/4 13:46
 * @Description: 主数据源注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Master {
}
