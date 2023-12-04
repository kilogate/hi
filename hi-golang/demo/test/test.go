package main

import (
	"context"
	"hi-golang/demo/logs"
)

func main() {
	ctx := context.Background()
	ctx = logs.NewContextWithLogID(ctx)
	logs.CtxInfo(ctx, "hello: %s", "Lask")
	logs.CtxInfo(ctx, "hello: %s", "Lask")
}
