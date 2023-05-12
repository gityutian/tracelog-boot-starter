package com.yutian.tracelog.autoconfigure;

import com.yutian.tracelog.constant.TraceLogConstant;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName MvcInterceptor
 * @Description
 * @Author Admin
 * @Date 2023/5/10 16:30
 * @Version 1.0
 */
public class MvcLogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceId = MDCUtils.getHeaderTraceId();
        MDC.clear();
        MDC.put(TraceLogConstant.TRACE_ID_KEY, traceId);
        return true;
    }
}
