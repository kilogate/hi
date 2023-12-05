package util

import (
	"context"
	"hi-golang/demo/logs"
	"os"
	"os/signal"
	"syscall"
)

// OnSignalNotify
// 使用示例
// go util.OnSignalNotify(ctx, func() {
//		logs.CtxInfo(ctx, "release resources")
//	})()
func OnSignalNotify(ctx context.Context, fn func()) func() {
	sig := make(chan os.Signal, 1)
	signal.Notify(sig, syscall.SIGINT)

	return func() {
		<-sig
		logs.CtxInfo(ctx, "signal notify")
		fn()
		logs.CtxInfo(ctx, "os exit")
		os.Exit(0)
	}
}
