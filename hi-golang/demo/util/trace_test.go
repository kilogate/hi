package util

import (
	"context"
	"testing"

	"hi-golang/demo/logs"

	"github.com/smartystreets/goconvey/convey"
)

func TestTrace(t *testing.T) {
	convey.Convey("TestToBytes", t, func() {
		ctx := context.Background()
		defer Trace(ctx)()

		logs.CtxInfo(ctx, "test start")
		logs.CtxInfo(ctx, "test end")
	})
}

func Test_getFuncName(t *testing.T) {
	convey.Convey("Test_getFuncName", t, func() {
		s := getFuncName()
		convey.So(s, convey.ShouldNotBeEmpty)
	})
}
