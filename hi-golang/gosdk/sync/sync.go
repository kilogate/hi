package main

import (
	"fmt"
	"sync"
)

func main() {
	for i := 0; i < 10; i++ {
		testWaitGroup()
	}

	for i := 0; i < 10; i++ {
		testMutex()
	}
}

func testWaitGroup() {
	var wg sync.WaitGroup

	count := 0
	for i := 0; i < 100; i++ {
		wg.Add(1) // 任务开始：加一
		go func() {
			defer wg.Done() // 任务完成：减一
			for j := 0; j < 100; j++ {
				count++
			}
		}()
	}

	wg.Wait() // 等待所有任务完成
	fmt.Printf("testWaitGroup end, count: %d\n", count)
}

func testMutex() {
	var wg sync.WaitGroup
	var mu sync.Mutex

	count := 0
	for i := 0; i < 100; i++ {
		wg.Add(1)
		go func() {
			defer wg.Done()
			for j := 0; j < 100; j++ {
				mu.Lock() // 加锁
				count++
				mu.Unlock() // 释放锁
			}
		}()
	}

	wg.Wait()
	fmt.Printf("testMutex end, count: %d\n", count)
}
