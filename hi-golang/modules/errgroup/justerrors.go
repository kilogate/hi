package main

import (
	"fmt"
	"log"
	"time"

	"golang.org/x/sync/errgroup"
)

func main() {
	log.Printf("main start")

	g := new(errgroup.Group)

	// 提交任务
	for i := 0; i < 100; i++ {
		i := i
		g.Go(func() error {
			return doWork(i)
		})
	}

	// 等待所有任务执行完成
	err := g.Wait()
	if err != nil {
		log.Printf("main err, err: %+v", err)
		return
	}

	log.Printf("main end")
}

func doWork(no int) error {
	time.Sleep(time.Duration(no%3) * time.Second)

	var err error
	if no == 500 { // 想测试err就改成0-99的值
		err = fmt.Errorf("5xx err")
	}

	log.Printf("doWork end, no: %d, err: %+v", no, err)
	return err
}
