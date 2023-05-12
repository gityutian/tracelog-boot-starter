package com.yutian.tracelog.autoconfigure;

import com.yutian.tracelog.constant.TraceLogConstant;
import feign.Feign;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName FeignRequestInterceptor
 * @Description
 * @Author Admin
 * @Date 2023/5/10 16:14
 * @Version 1.0
 */
@Configuration
@ConditionalOnClass({Feign.class})
public class FeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String traceId = MDC.get(TraceLogConstant.TRACE_ID_KEY);
        if(StringUtils.isNotBlank(traceId)){
            requestTemplate.header(TraceLogConstant.TRACE_ID_KEY, traceId);
        }
    }
}
