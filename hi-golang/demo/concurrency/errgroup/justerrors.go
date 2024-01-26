package main

import (
	"context"
	"fmt"
	"time"

	"hi-golang/demo/logs"

	"golang.org/x/sync/errgroup"
)

func main() {
	ctx := logs.NewContextWithLogID(context.Background())

	logs.CtxInfo(ctx, "main start")

	group, ctx := errgroup.WithContext(ctx)

	// 提交任务
	for i := 0; i < 100; i++ {
		i := i
		group.Go(func() error {
			return doWork(ctx, i)
		})
	}

	// 等待所有任务执行完成
	err := group.Wait()
	if err != nil {
		logs.CtxError(ctx, "main err, err: %+v", err)
		return
	}

	logs.CtxInfo(ctx, "main end")
}

func doWork(ctx context.Context, no int) error {
	time.Sleep(time.Duration(no%3) * time.Second)

	var err error
	if no == 3 { // 想测试err就改成0-99的值
		err = fmt.Errorf("5xx err")
	}

	logs.CtxInfo(ctx, "doWork end, no: %d, err: %+v", no, err)
	return err
}
