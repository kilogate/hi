package util

import (
	"context"
	"testing"

	"hi-golang/demo/logs"

	"github.com/smartystreets/goconvey/convey"
)

func TestRecoverFromPanic(t *testing.T) {
	convey.Convey("TestRecoverFromPanic", t, func() {
		f1()
	})
}

func f1() {
	ctx := context.Background()
	defer RecoverFromPanic(ctx, "f1")

	i := 5 / 19
	logs.CtxInfo(ctx, "res: %d", 1/i)
}
