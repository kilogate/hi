package com.hi.spring.ioc.scope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * ServiceA
 *
 * @author fengquanwei
 * @create 2023/10/24 14:15
 **/
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON) // 作用域：单例（默认作用域）
public class ServiceA {
}
