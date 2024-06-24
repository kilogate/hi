package com.kilogate.hi.mockito;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

/**
 * MockitoUsage
 *
 * @author fengquanwei
 * @create 2023/8/14 10:31
 **/
@Slf4j
public class MockitoUsage {
    /**
     * 验证行为（即验证某个方法及对应参数是否被调用了）
     */
    @Test
    void testBehavior() {
        // mock List
        List<String> list = Mockito.mock(List.class);
        list.add("1");
        list.add("2");

        log.info(list.get(0)); // 会得到 null ，前面只是在记录行为而已，没有往 list 中添加数据

        Mockito.verify(list).add("1"); // 正确，因为该行为被记住了
//        Mockito.verify(list).add("3"); // 报错，因为前面没有记录这个行为
    }

    /**
     * 打桩
     */
    @Test
    void testStub() {
        List<Integer> list = Mockito.mock(ArrayList.class);

        Mockito.when(list.get(0)).thenReturn(10);
        Mockito.when(list.get(1)).thenReturn(20);
        Mockito.when(list.get(2)).thenThrow(new RuntimeException("no such element"));

        Assertions.assertEquals(list.get(0), 10);
        Assertions.assertEquals(list.get(1), 20);
        Assertions.assertNull(list.get(4));
        Assertions.assertThrows(RuntimeException.class, () -> {
            int x = list.get(2);
        });
    }

    /**
     * void 方法打桩
     */
    @Test
    void testVoidStub() {
        List<Integer> list = Mockito.mock(ArrayList.class);
        Mockito.doReturn(10).when(list).get(1);
        Mockito.doThrow(new RuntimeException("you cant clear this List")).when(list).clear();

        Assertions.assertEquals(list.get(1), 10);
        Assertions.assertThrows(RuntimeException.class, () -> list.clear());
    }

    /**
     * 参数匹配器
     */
    @Test
    void testMatchers() {
        List<Integer> list = Mockito.mock(ArrayList.class);
        Mockito.when(list.get(Mockito.anyInt())).thenReturn(100);

        Assertions.assertEquals(list.get(999), 100);
    }

    /**
     * 调用次数
     */
    @Test
    void testTimes() {
        List<Integer> list = Mockito.mock(ArrayList.class);
        Mockito.when(list.get(Mockito.anyInt())).thenReturn(100);
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        System.out.println(list.get(2));
        System.out.println(list.get(2));
        System.out.println(list.get(2));

        Mockito.verify(list, Mockito.times(7)).get(Mockito.anyInt());
        Mockito.verify(list, Mockito.atLeastOnce()).get(0);
        Mockito.verify(list, Mockito.atLeast(3)).get(2);
        Mockito.verify(list, Mockito.atMost(6)).get(2);
    }

}
