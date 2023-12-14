package mockey

import (
	"testing"

	. "github.com/bytedance/mockey"
	. "github.com/smartystreets/goconvey/convey"
)

// cd hi-golang/demo/test/mockey/ && go test -gcflags="all=-l -N" -v
func TestMockey(t *testing.T) {
	PatchConvey("测试Mockey", t, func() { // PatchConvey外自动释放mock
		// mock函数
		Mock(Hello).Return("abc").Build()
		So(Hello("Tom"), ShouldEqual, "abc")

		Mock(Hi).To(func(name string) string {
			return "abc"
		}).Build()
		So(Hi("Tom"), ShouldEqual, "abc")

		// mock方法
		Mock((*service).Hello).Return("abc").Build()
		So(Service.Hello("Tom"), ShouldEqual, "abc")

		Mock((*service).Hi).To(func(name string) string {
			return "abc"
		}).Build()
		So(Service.Hi("Tom"), ShouldEqual, "abc")

		// mock变量
		MockValue(&num).To(100)
		So(num, ShouldEqual, 100)

		// mock函数变量
		MockValue(&Hey).To(func(name string) string {
			return "abc"
		})
		So(Hey("Tom"), ShouldEqual, "abc")
	})
}
