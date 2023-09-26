package com.hi.spring.ioc.autoconfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * AutoConfigTest
 *
 * @author fengquanwei
 * @create 2023/9/26 20:09
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class AutoConfigTest {
    @Autowired
    private ServiceA serviceA;

    @Test
    public void test() {
        serviceA.hello();
    }
}
