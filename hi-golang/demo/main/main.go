package main

import (
	"context"
	"time"

	"hi-golang/demo/logs"
	"hi-golang/demo/util"
)

func main() {
	TestSignal()
}

func TestSignal() {
	ctx := context.Background()

	go util.OnSignalNotify(ctx, func() {
		logs.CtxInfo(ctx, "release resources")
	})()

	logs.CtxInfo(ctx, "test start")
	time.Sleep(time.Minute)
	logs.CtxInfo(ctx, "test end")
}
