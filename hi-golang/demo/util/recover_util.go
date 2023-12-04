package util

import (
	"context"
	"hi-golang/demo/logs"
	"runtime/debug"
)

func RecoverFromPanic(ctx context.Context, msg string) {
	if p := recover(); p != nil {
		logs.CtxError(ctx, "goroutine occurs panic, msg: %s, panic: %+v\n%+v", msg, p, string(debug.Stack()))
	}
}
