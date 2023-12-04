package util

import (
	"context"
	"hi-golang/demo/logs"
	"runtime/debug"
)

// RecoverFromPanic
// 使用示例：defer util.RecoverFromPanic(context.Background(), "f")
func RecoverFromPanic(ctx context.Context, msg string) {
	if p := recover(); p != nil {
		logs.CtxError(ctx, "goroutine occurs panic, msg: %s, panic: %+v\n%+v", msg, p, string(debug.Stack()))
	}
}
