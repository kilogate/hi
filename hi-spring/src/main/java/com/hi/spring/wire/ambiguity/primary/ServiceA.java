package com.hi.spring.wire.ambiguity.primary;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * ServiceA
 *
 * @author fengquanwei
 * @create 2023/10/9 10:20
 **/
@Component
@Primary
public class ServiceA implements Service{
}
