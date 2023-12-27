package mockey

import (
	"testing"

	"github.com/bytedance/mockey"
	"github.com/smartystreets/goconvey/convey"
)

// cd hi-golang/demo/test/mockey/ && go test -gcflags="all=-l -N" -v
func TestMockey(t *testing.T) {
	mockey.PatchConvey("测试Mockey", t, func() { // PatchConvey外自动释放mock
		// mock函数
		mockey.Mock(Hello).Return("abc").Build()
		convey.So(Hello("Tom"), convey.ShouldEqual, "abc")

		mockey.Mock(Hi).To(func(name string) string {
			return "abc"
		}).Build()
		convey.So(Hi("Tom"), convey.ShouldEqual, "abc")

		// mock方法
		mockey.Mock((*service).Hello).Return("abc").Build()
		convey.So(Service.Hello("Tom"), convey.ShouldEqual, "abc")

		mockey.Mock((*service).Hi).To(func(name string) string {
			return "abc"
		}).Build()
		convey.So(Service.Hi("Tom"), convey.ShouldEqual, "abc")

		// 获取方法
		method := mockey.GetMethod(Service, "Bye")
		mockey.Mock(method).Return("xxx").Build()
		convey.So(Service.Bye("Tom"), convey.ShouldEqual, "xxx")

		// mock变量
		mockey.MockValue(&num).To(100)
		convey.So(num, convey.ShouldEqual, 100)

		// mock函数变量
		mockey.MockValue(&Hey).To(func(name string) string {
			return "abc"
		})
		convey.So(Hey("Tom"), convey.ShouldEqual, "abc")
	})
}
