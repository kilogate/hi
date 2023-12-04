package main

import (
	"context"
	"hi-golang/demo/logger"
)

func main() {
	ctx := context.Background()
	ctx = logger.NewContextWithLogID(ctx)
	logger.Info(ctx, "hello: %s", "Lask")
	logger.Info(ctx, "hello: %s", "Lask")
}
