package util

import (
	"context"
	"runtime/debug"

	"hi-golang/demo/logs"
)

// RecoverFromPanic
// 使用示例：defer util.RecoverFromPanic(context.Background(), "f1")
func RecoverFromPanic(ctx context.Context, msg string) {
	if p := recover(); p != nil {
		logs.CtxError(ctx, "goroutine occurs panic, msg: %s, panic: %+v\n%+v", msg, p, string(debug.Stack()))
	}
}
