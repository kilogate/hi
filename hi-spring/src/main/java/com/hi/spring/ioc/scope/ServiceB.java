package com.hi.spring.ioc.scope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * ServiceB
 *
 * @author fengquanwei
 * @create 2023/10/24 14:18
 **/
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE) // 作用域：原型
public class ServiceB {
}
