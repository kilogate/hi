package main

import (
	"context"
	"hi-golang/demo/logs"
	"hi-golang/demo/util"
	"time"
)

func main() {
	bigSlowOperation(context.Background())
}

func bigSlowOperation(ctx context.Context) {
	go util.OnSignalNotify(ctx, func() {
		logs.CtxInfo(ctx, "release resources")
	})()

	logs.CtxInfo(ctx, "bigSlowOperation start")
	time.Sleep(3 * time.Second)
	logs.CtxInfo(ctx, "bigSlowOperation end")
}
