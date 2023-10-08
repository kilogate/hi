package com.hi.spring.ioc.config.mixedconfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * MixedConfigTest
 *
 * @author fengquanwei
 * @create 2023/10/8 10:22
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class MixedConfigTest {
    @Autowired
    private Service service;

    @Test
    public void test() {
        service.hello();
    }
}
