package com.yutian.tracelog.autoconfigure;

import com.yutian.tracelog.xxljob.XxlJobHandle;
import com.xxl.job.core.context.XxlJobContext;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName XXLJobLogAspect
 * @Description
 * @Author Admin
 * @Date 2023/5/11 16:21
 * @Version 1.0
 */
@Aspect
@Configuration
@ConditionalOnClass({XxlJobContext.class})
public class XXLJobLogAspect{

    @Value("${tracelog.point.execution.express.xxljob:execution(* com.yutian.tracelog.autoconfigure..*.*(..))}")
    private String xxlJobExpression;

    @Bean
    public AspectJExpressionPointcutAdvisor xxlJobLogVisor() {
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression(xxlJobExpression);
        advisor.setAdvice(new XxlJobHandle());
        return advisor;
    }
}
