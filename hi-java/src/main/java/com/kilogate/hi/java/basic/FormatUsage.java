package com.kilogate.hi.java.basic;

import java.util.Date;

/**
 * 格式化的用法
 * <p>
 * 格式说明符：%<参数索引>$<标志><宽度>.<精度><转换符>
 *
 * @author fengquanwei
 * @create 2020/8/5 上午12:12
 **/
public class FormatUsage {
    public static void main(String[] args) {
        转换符();

        日期和时间转换符();

        标志();
    }

    private static void 转换符() {
        // d：十进制整数（示例：159）
        System.out.printf("%d%n", 159);

        // x：十六进制整数（示例：9f）
        System.out.printf("%x%n", 159);

        // o：八进制整数（示例：237）
        System.out.printf("%o%n", 159);

        // f：定点浮点数（示例：15.9）
        System.out.printf("%f%n", 15.9);

        // e：指数浮点数（示例：1.59e+01）
        System.out.printf("%e%n", 15.9);

        // g：通用浮点数
        System.out.printf("%g%n", 15.9);

        // a：十六进制浮点数（示例：0x1.fccdp3）
        System.out.printf("%a%n", 15.9);

        // s：字符串（示例：Hello）
        System.out.printf("%s%n", "Hello");

        // c：字符（示例：H）
        System.out.printf("%c%n", 'H');

        // b：布尔（示例：true）
        System.out.printf("%b%n", true);

        // h：散列码（示例：42628b2）
        System.out.printf("%h%n", new Object().hashCode());

        // %：百分号
        // n：换行符
        System.out.printf("%%%n");
    }

    private static void 日期和时间转换符() {
        // tB：月的完整拼写（示例：六月）
        System.out.printf("%tB%n", new Date());

        // tb：月的缩写（示例：六月）
        System.out.printf("%tb%n", new Date());

        // th：月的缩写（示例：六月）
        System.out.printf("%th%n", new Date());

        // tm：两位数字的月（前面补 0）（示例：06）
        System.out.printf("%tm%n", new Date());

        // td：两位数字的日（前面补 0）（示例：05）
        System.out.printf("%td%n", new Date());

        // te：两位数字的日（前面不补 0）（示例：5）
        System.out.printf("%te%n", new Date());

        // tA：星期几的完整拼写（示例：星期三）
        System.out.printf("%tA%n", new Date());

        // ta：星期几的缩写（示例：星期三）
        System.out.printf("%ta%n", new Date());

        // tj：三位数的年中的日子（前面补 0），在 001 到 366 之间（示例：156）
        System.out.printf("%tj%n", new Date());

        // tH：两位数字中的小时（前面补 0），在 0 到 23 之间（示例：23）
        System.out.printf("%tH%n", new Date());

        // tk：两位数字中的小时（前面不补 0），在 0 到 23 之间（示例：23）
        System.out.printf("%tk%n", new Date());

        // tI：两位数字中的小时（前面补 0），在 0 到 12 之间（示例：11）
        System.out.printf("%tI%n", new Date());

        // tl：两位数字中的小时（前面不补 0），在 0 到 12 之间（示例：11）
        System.out.printf("%tl%n", new Date());

        // tM：两位数字的分钟（前面补 0）（示例：22）
        System.out.printf("%tM%n", new Date());

        // tS：两位数字的秒（前面补 0）（示例：14）
        System.out.printf("%tS%n", new Date());

        // tL：三位数字的毫秒（前面补 0）（示例：759）
        System.out.printf("%tL%n", new Date());

        // tN：九位数字的微秒（前面补 0）（示例：759000000）
        System.out.printf("%tN%n", new Date());

        // tp：上午或下午的标志（示例：下午）
        System.out.printf("%tp%n", new Date());

        // tz：从 GMT 起，RFC822 数字位移（示例：+0800）
        System.out.printf("%tz%n", new Date());

        // tZ：时区（示例：CST）
        System.out.printf("%tZ%n", new Date());

        // ts：从格林威治时间 1970-01-01 00:00:00 起的秒数（示例：1559748134）
        System.out.printf("%ts%n", new Date());

        // tQ：从格林威治时间 1970-01-01 00:00:00 起的毫秒数（示例：1559748134761）
        System.out.printf("%tQ%n", new Date());
    }

    private static void 标志() {
        // +：打印正数和负数的符号（示例：+3333.33）
        System.out.printf("%+f%n", 3333.33);

        // 空格：在正数之前添加空格（示例：| 3333.33|）
        System.out.printf("% f%n", 3333.33);

        // 0：数字前面补 0（示例：003333.33）
        System.out.printf("%013f%n", 3333.33);

        // -：左对齐（示例：|3333.33 |）
        System.out.printf("%-13f%n", 3333.33);

        // (：将负数括在括号内（示例：(3333.33)）
        System.out.printf("%(f%n", -3333.33);

        // ,：添加分组分隔符（示例：3,333.33）
        System.out.printf("%,f%n", 3333.33);

        // #：对于 x 或 o 添加前缀 0x 或 0（示例：0xcafe）
        System.out.printf("%#x%n", 0xcafe);

        // $：给定被格式化的参数索引，例如 %1$d 以十进制打印第一个参数
        System.out.printf("%5$d %4$d %3$d %2$d %1$d %n", 1, 2, 3, 4, 5);

        // <：格式化前面说明的数值
        System.out.printf("%d %<d %<d %<d %<d %n", 1, 2, 3, 4, 5);

        // 宽度.精度
        System.out.printf("%4.2f%n", 3.141592654);
    }
}
