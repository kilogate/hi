package goconvey

import (
	. "github.com/smartystreets/goconvey/convey"
	"testing"
)

// cd hi-golang/demo/test/goconvey/ && go test -v -run=TestHello
func TestHello(t *testing.T) {
	Convey("正常测试用例", t, func() {
		hello := Hello("Tom")
		So(hello, ShouldEqual, "Hello Tom")
		So(hello, ShouldNotBeEmpty)
	})
}

// cd hi-golang/demo/test/goconvey/ && go test -v -run=TestService_Hello
func TestService_Hello(t *testing.T) {
	Convey("最外层测试用例", t, func() {
		s := &Service{}

		Convey("嵌套测试用例1", func() {
			hello := s.Hello("Tom")
			So(hello, ShouldEqual, "Hello Tom")
		})

		Convey("嵌套测试用例2", func() {
			hello := s.Hello("Tom")
			So(hello, ShouldNotBeEmpty)
		})
	})
}
