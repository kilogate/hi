package com.kilogate.hi.mockito;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test1
 *
 * @author fengquanwei
 * @create 2023/8/14 10:31
 **/
@Slf4j
public class Test1 {

    /**
     * 验证行为
     */
    @Test
    void testBehavior() {
        // 构建moock数据
        List<String> list = mock(List.class);
        list.add("1");
        list.add("2");

        log.info(list.get(0)); // 会得到null ，前面只是在记录行为而已，没有往list中添加数据

        verify(list).add("1"); // 正确，因为该行为被记住了
//        verify(list).add("3"); // 报错，因为前面没有记录这个行为
    }

    /**
     * 打桩
     */
    @Test
    void testStub() {
        List<Integer> list = mock(ArrayList.class);

        when(list.get(0)).thenReturn(10);
        when(list.get(1)).thenReturn(20);
        when(list.get(2)).thenThrow(new RuntimeException("no such element"));

        assertEquals(list.get(0), 10);
        assertEquals(list.get(1), 20);
        assertNull(list.get(4));
        assertThrows(RuntimeException.class, () -> {
            int x = list.get(2);
        });
    }

    /**
     * void方法打桩
     */
    @Test
    void testVoidStub(){
        List<Integer> list = mock(ArrayList.class);
        doReturn(10).when(list).get(1);
        doThrow(new RuntimeException("you cant clear this List")).when(list).clear();

        assertEquals(list.get(1),10);
        assertThrows(RuntimeException.class,()->list.clear());
    }

    /**
     * 参数匹配器
     */
    @Test
    void testMatchers() {
        List<Integer> list = mock(ArrayList.class);
        when(list.get(anyInt())).thenReturn(100);

        assertEquals(list.get(999),100);
    }

    /**
     * 调用次数
     */
    @Test
    void testTimes() {
        List<Integer> list = mock(ArrayList.class);
        when(list.get(anyInt())).thenReturn(100);
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        System.out.println(list.get(2));
        System.out.println(list.get(2));
        System.out.println(list.get(2));

        verify(list, times(7)).get(anyInt());
        verify(list, atLeastOnce()).get(0);
        verify(list, atLeast(3)).get(2);
        verify(list, atMost(6)).get(2);
    }

}
