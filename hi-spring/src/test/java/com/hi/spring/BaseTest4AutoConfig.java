package com.hi.spring;

import com.hi.spring.ioc.autoconfig.Config4AutoConfig;
import com.hi.spring.ioc.autoconfig.Service4AutoConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * BaseTest4AutoConfig
 *
 * @author fengquanwei
 * @create 2022/5/4 22:20
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config4AutoConfig.class)
public class BaseTest4AutoConfig {
    @Autowired
    private Service4AutoConfig service4AutoConfig;

    @Test
    public void testAutoConfig() {
        service4AutoConfig.sayHello();
    }
}
