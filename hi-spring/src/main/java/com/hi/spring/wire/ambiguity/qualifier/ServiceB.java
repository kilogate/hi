package com.hi.spring.wire.ambiguity.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * ServiceB
 *
 * @author fengquanwei
 * @create 2023/10/9 10:20
 **/
@Component
@Qualifier("BBB") // 自定义限定符为 BBB，默认的限定符是 bean id
public class ServiceB implements Service {
}
