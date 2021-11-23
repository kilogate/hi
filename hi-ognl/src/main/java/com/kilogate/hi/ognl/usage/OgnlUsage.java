package com.kilogate.hi.ognl.usage;

import lombok.*;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

import java.util.HashMap;
import java.util.Map;

/**
 * OgnlUsage
 *
 * @author kilogate
 * @create 2021/11/23 11:32
 **/
public class OgnlUsage {
    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    private static class User {
        private int no;
        private String name;
    }

    public static void main(String[] args) throws OgnlException {
        // 构造上下文
        Map<String, Object> values = new HashMap<>();

        OgnlContext context = new OgnlContext(values);
        context.setRoot(new User(1, "Root"));
        context.put("user", new User(7, "Lask"));

        // 获取根元素
        Object root = context.getRoot();
        System.out.println(root);

        // 解析表达式（Math类的方法直接调用）
        Object expression = Ognl.parseExpression("@@min(10,4)");

        // 执行表达式
        Object result = Ognl.getValue(expression, context, root);
        System.out.println(result);

        // 一、获取值
        Object value1 = Ognl.getValue(Ognl.parseExpression("#user"), context, root);
        System.out.println(value1);

        // 二、执行方法
        Object value2 = Ognl.getValue(Ognl.parseExpression("#user.name.toUpperCase()"), context, root);
        System.out.println(value2);

        // 三、执行静态方法
        Object value3 = Ognl.getValue(Ognl.parseExpression("@java.lang.Integer@toBinaryString(13)"), context, root);
        System.out.println(value3);
    }
}
