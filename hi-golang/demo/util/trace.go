package util

import (
	"context"
	"hi-golang/demo/logs"
	"runtime"
	"time"
)

// Trace
// 使用示例：defer util.Trace(ctx)()
func Trace(ctx context.Context) func() {
	start := time.Now()
	funcName := getFuncName()
	logs.CtxInfo(ctx, "enter %s", funcName)
	return func() {
		logs.CtxInfo(ctx, "exit %s, cost %s", funcName, time.Since(start))
	}
}

func getFuncName() string {
	pc := make([]uintptr, 1)
	runtime.Callers(3, pc)
	f := runtime.FuncForPC(pc[0])
	return f.Name()
}
