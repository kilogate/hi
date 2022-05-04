package com.hi.spring;

import com.hi.spring.ioc.autoconfig.Config;
import com.hi.spring.ioc.autoconfig.ServiceA;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * BaseTest4AutoConfigIoc
 *
 * @author fengquanwei
 * @create 2022/5/4 22:20
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class BaseTest4AutoConfigIoc {
    @Autowired
    private ServiceA serviceA;

    @Test
    public void test() {
        serviceA.hello();
    }
}
