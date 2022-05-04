package com.hi.spring;

import com.hi.spring.ioc.xmlconfig.ServiceA;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * BaseTest
 *
 * @author fengquanwei
 * @create 2022/5/4 22:20
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class BaseTest {
    @Autowired
    private ServiceA serviceA;

    @Test
    public void testXmlConfig() {
        serviceA.sayHello();
    }
}
