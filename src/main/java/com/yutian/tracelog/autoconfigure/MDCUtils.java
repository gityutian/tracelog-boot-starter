package com.yutian.tracelog.autoconfigure;

import com.yutian.tracelog.constant.TraceLogConstant;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;

public class MDCUtils {

    /**
     * [获取 traceId]
     * @return java.lang.String
     **/
    public static String getHeaderTraceId(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);
        String traceId;
        if (request.getHeader(TraceLogConstant.TRACE_ID_KEY) == null) {
            traceId = createTraceId();
        } else {
            traceId = request.getHeader(TraceLogConstant.TRACE_ID_KEY);
        }
        return traceId;
    }

    public static String getTraceId(){
        if(StringUtils.isNotBlank(MDC.get(TraceLogConstant.TRACE_ID_KEY))){
            return MDC.get(TraceLogConstant.TRACE_ID_KEY);
        }else{
            String traceId = createTraceId();
            MDC.put(TraceLogConstant.TRACE_ID_KEY, traceId);
            return traceId;
        }
    }

    public static String createTraceId() {
        String uuid = UUID.randomUUID().toString();
        return DigestUtils.md5Hex(uuid).substring(8, 24);
    }

    public static <T> Callable<T> wrap(final Callable<T> callable, final Map<String, String> context) {
        return () -> {
            if (CollectionUtils.isEmpty(context)) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            try {
                return callable.call();
            } finally {
                // 清除子线程的，避免内存溢出，就和ThreadLocal.remove()一个原因
                MDC.clear();
            }
        };
    }

    public static Runnable wrap(final Runnable runnable, final Map<String, String> context) {
        return () -> {
            if (CollectionUtils.isEmpty(context)) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            try {
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }
}