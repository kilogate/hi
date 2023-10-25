package com.hi.spring.ioc.xmlconfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * XmlConfigTest
 *
 * @author fengquanwei
 * @create 2023/9/26 20:12
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:ioc/xmlconfig/spring.xml"})
public class XmlConfigTest {
    @Autowired
    private ServiceA serviceA;

    @Test
    public void test() {
        serviceA.hello();
    }
}
