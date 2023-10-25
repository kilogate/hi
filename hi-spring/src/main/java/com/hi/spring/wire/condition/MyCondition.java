package com.hi.spring.wire.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * MyCondition
 *
 * @author fengquanwei
 * @create 2023/10/8 11:44
 **/
public class MyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        return true;
    }
}
