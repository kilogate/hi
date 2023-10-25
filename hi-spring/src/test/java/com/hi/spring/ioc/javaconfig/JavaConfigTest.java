package com.hi.spring.ioc.javaconfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * JavaConfigTest
 *
 * @author fengquanwei
 * @create 2023/10/8 10:21
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class JavaConfigTest {
    @Autowired
    private ServiceA serviceA;

    @Test
    public void test() {
        serviceA.hello();
    }
}
