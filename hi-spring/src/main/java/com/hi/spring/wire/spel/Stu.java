package com.hi.spring.wire.spel;

import org.springframework.stereotype.Component;

/**
 * Stu
 *
 * @author fengquanwei
 * @create 2023/10/25 15:10
 **/
@Component
public class Stu {
    public String name = "Tom";
    public int age = 17;
    public String email = "tom@163.com";

    public String getName() {
        return this.name;
    }
}
