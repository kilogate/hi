package main

import (
	"context"
	"fmt"
	"sync"
	"time"

	"hi-golang/demo/logs"
	"hi-golang/demo/util"

	"golang.org/x/sync/errgroup"
)

func main() {
	ctx := util.NewCtx()

	logs.CtxInfo(ctx, "main start")

	// 创建协程池
	group, ctx := errgroup.WithContext(ctx)
	group.SetLimit(10) // 设置最大协程数

	// 存储执行结果的并发Map
	resMap := &sync.Map{}

	// 提交任务
	keys := []string{"a", "b", "c"} // 加个"err"就报错
	for _, key := range keys {
		key := key
		group.Go(func() error {
			return doWorkWithResult(ctx, key, resMap)
		})
	}

	// 等待所有任务执行完成
	err := group.Wait()
	if err != nil {
		logs.CtxError(ctx, "main err, err: %+v", err)
		return
	}

	// 遍历结果
	resMap.Range(func(key, value any) bool {
		logs.CtxInfo(ctx, "key: %s, value: %s", key, value)
		return true
	})

	logs.CtxInfo(ctx, "main end")
}

func doWorkWithResult(ctx context.Context, key string, resMap *sync.Map) error {
	time.Sleep(2 * time.Second)

	if key == "err" {
		logs.CtxError(ctx, "doWorkWithResult err, key: %s", key)
		return fmt.Errorf("doWorkWithResult err")
	}

	resMap.Store(key, key)
	logs.CtxInfo(ctx, "doWorkWithResult end, key: %s", key)
	return nil
}
