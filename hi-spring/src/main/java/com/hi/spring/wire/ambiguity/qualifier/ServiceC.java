package com.hi.spring.wire.ambiguity.qualifier;

import org.springframework.stereotype.Component;

/**
 * ServiceC
 *
 * @author fengquanwei
 * @create 2023/10/9 10:40
 **/
@Component
@QualifierC1 // 使用自定义注解
@QualifierC2 // 组合使用多个注解
public class ServiceC implements Service {
}
