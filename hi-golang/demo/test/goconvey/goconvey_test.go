package goconvey

import (
	"testing"

	"github.com/smartystreets/goconvey/convey"
)

// cd hi-golang/demo/test/goconvey/ && go test -v -run=TestHello
func TestHello(t *testing.T) {
	convey.Convey("正常测试用例", t, func() {
		hello := Hello("Tom")
		convey.So(hello, convey.ShouldEqual, "Hello Tom")
		convey.So(hello, convey.ShouldNotBeEmpty)
	})
}

// cd hi-golang/demo/test/goconvey/ && go test -v -run=TestService_Hello
func TestService_Hello(t *testing.T) {
	convey.Convey("最外层测试用例", t, func() {
		s := &Service{}

		convey.Convey("嵌套测试用例1", func() {
			hello := s.Hello("Tom")
			convey.So(hello, convey.ShouldEqual, "Hello Tom")
		})

		convey.Convey("嵌套测试用例2", func() {
			hello := s.Hello("Tom")
			convey.So(hello, convey.ShouldNotBeEmpty)
		})
	})
}
