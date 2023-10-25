package com.hi.spring.wire.list;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * App
 *
 * @author fengquanwei
 * @create 2023/10/20 15:29
 **/
@Component
public class App {
    @Resource // spring 会自动将所有实现了 Service 接口的组件组装成 List 来实现自动装配
    private List<Service> serviceList;
}
