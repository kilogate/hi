package main

import (
	"context"
	"fmt"
	"log"
	"sync"
	"time"

	"golang.org/x/sync/errgroup"
)

func main() {
	log.Printf("main start")

	g, ctx := errgroup.WithContext(context.Background())
	keys := []string{"a", "b", "c"} // 加个"err"就报错
	resMap := &sync.Map{}

	// 提交任务
	for _, key := range keys {
		key := key
		g.Go(func() error {
			return doWorkWithResult(ctx, key, resMap)
		})
	}

	// 等待所有任务执行完成
	err := g.Wait()
	if err != nil {
		log.Printf("main err, err: %+v", err)
		return
	}
	resMap.Range(func(key, value any) bool {
		log.Printf("key: %s, value: %s\n", key, value)
		return true
	})

	log.Printf("main end")
}

func doWorkWithResult(_ context.Context, key string, resMap *sync.Map) error {
	time.Sleep(2 * time.Second)

	if key == "err" {
		log.Fatalf("doWorkWithResult err, key: %s", key)
		return fmt.Errorf("doWorkWithResult err")
	}

	resMap.Store(key, key)
	log.Printf("doWorkWithResult end, key: %s", key)
	return nil
}
