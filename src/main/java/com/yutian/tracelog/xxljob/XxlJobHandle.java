package com.yutian.tracelog.xxljob;

import com.yutian.tracelog.autoconfigure.MDCUtils;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @ClassName XXLJobHandle
 * @Description
 * @Author Admin
 * @Date 2023/5/12 16:28
 * @Version 1.0
 */
public class XxlJobHandle implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        MDCUtils.getTraceId();
        return methodInvocation.proceed();
    }
}
