package com.hi.spring.ioc.profile;

import com.hi.spring.wire.profile.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * ProfileTest
 *
 * @author fengquanwei
 * @create 2023/10/8 11:24
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
@ActiveProfiles({"A", "B", "C"})
public class ProfileTest {
    @Autowired(required = false)
    private ServiceA serviceA;
    @Autowired(required = false)
    private ServiceB serviceB;
    @Autowired(required = false)
    private ServiceC serviceC;
    @Autowired(required = false)
    private ServiceD serviceD;

    @Test
    public void test() {
        System.out.println("serviceA = " + serviceA);
        System.out.println("serviceB = " + serviceB);
        System.out.println("serviceC = " + serviceC);
        System.out.println("serviceD = " + serviceD);
    }

}
