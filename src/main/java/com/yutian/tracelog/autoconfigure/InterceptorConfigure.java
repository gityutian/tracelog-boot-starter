package com.yutian.tracelog.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName FeignRequestInterceptor
 * @Description //TODO
 * @Author Admin
 * @Date 2023/5/10 16:14
 * @Version 1.0
 */
@Configuration
@ConditionalOnClass({WebApplicationContext.class})
public class InterceptorConfigure implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new MvcLogInterceptor());
        registration.addPathPatterns("/**");
    }
}
