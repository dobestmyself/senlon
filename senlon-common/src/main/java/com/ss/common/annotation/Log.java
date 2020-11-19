package com.ss.common.annotation;

import com.ss.common.enums.BusinessType;
import com.ss.common.enums.OperatorType;

import java.lang.annotation.*;

/**
 * 自定义操作日志记录注解
 * 
 * @author shishuai
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log
{
    /**
     * 模块 
     */
    public String title() default "";

    /**
     * 功能
     */
    public BusinessType businessType() default BusinessType.OTHER;

    /**
     * 操作人类别
     */
    public OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * 是否保存请求的参数
     */
    public boolean isSaveRequestData() default true;
}
