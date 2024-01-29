package util

import (
	"context"

	"hi-golang/demo/logs"
)

func NewCtx() context.Context {
	return logs.NewContextWithLogID(context.Background())
}
