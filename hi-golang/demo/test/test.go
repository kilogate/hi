package main

import (
	"context"
	"hi-golang/demo/util"
	"time"
)

func main() {
	bigSlowOperation(context.Background())
}

func bigSlowOperation(ctx context.Context) {
	defer util.Trace(ctx)()
	time.Sleep(3 * time.Second)
}
