package gomonkey

import (
	"reflect"
	"testing"

	"github.com/agiledragon/gomonkey"
	. "github.com/smartystreets/goconvey/convey"
)

// cd hi-golang/demo/test/gomonkey/ && GOARCH=amd64 go test -gcflags=all=-l -v -run=TestApplyFuncDemo
func TestApplyFuncDemo(t *testing.T) {
	Convey("Mock函数", t, func() {

		Convey("Mock单个函数", func() {
			patches := gomonkey.ApplyFunc(Hello, func(_ string) string {
				return "abc"
			})
			defer patches.Reset()

			hello := Hello("Tom")
			So(hello, ShouldEqual, "abc")
		})

		Convey("Mock多个函数", func() {
			patches := gomonkey.ApplyFunc(Hello, func(_ string) string {
				return "abc"
			})
			defer patches.Reset()
			gomonkey.ApplyFunc(Hi, func(_ string) string {
				return "abc"
			})

			hello := Hello("Tom")
			So(hello, ShouldEqual, "abc")

			hi := Hi("Tom")
			So(hi, ShouldEqual, "abc")
		})

	})
}

// cd hi-golang/demo/test/gomonkey/ && GOARCH=amd64 go test -gcflags=all=-l -v -run=TestApplyMethodDemo
func TestApplyMethodDemo(t *testing.T) {
	Convey("Mock方法", t, func() {

		Convey("Mock单个方法", func() {
			patches := gomonkey.ApplyMethod(reflect.TypeOf(Service), "Hello", func(_ *service, _ string) string {
				return "abc"
			})
			defer patches.Reset()

			hello := Service.Hello("Tom")
			So(hello, ShouldEqual, "abc")
		})

		Convey("Mock多个方法", func() {
			patches := gomonkey.ApplyMethod(reflect.TypeOf(Service), "Hello", func(_ *service, _ string) string {
				return "abc"
			})
			defer patches.Reset()
			patches.ApplyMethod(reflect.TypeOf(Service), "Hi", func(_ *service, _ string) string {
				return "abc"
			})

			hello := Service.Hello("Tom")
			So(hello, ShouldEqual, "abc")

			hi := Service.Hi("Tom")
			So(hi, ShouldEqual, "abc")
		})

	})
}

// cd hi-golang/demo/test/gomonkey/ && GOARCH=amd64 go test -gcflags=all=-l -v -run=TestApplyGlobalVar
func TestApplyGlobalVar(t *testing.T) {
	Convey("Mock全局变量", t, func() {

		Convey("Mock全局变量", func() {
			patches := gomonkey.ApplyGlobalVar(&num, 150)
			defer patches.Reset()
			So(num, ShouldEqual, 150)
		})

		Convey("恢复全局变量", func() {
			So(num, ShouldEqual, 10)
		})

	})
}

// cd hi-golang/demo/test/gomonkey/ && GOARCH=amd64 go test -gcflags=all=-l -v -run=TestApplyFuncVarDemo
func TestApplyFuncVarDemo(t *testing.T) {
	Convey("Mock函数变量", t, func() {
		patches := gomonkey.ApplyFuncVar(&Hey, func(name string) string {
			return "abc"
		})
		defer patches.Reset()

		hey := Hey("Tom")
		So(hey, ShouldEqual, "abc")
	})
}

// cd hi-golang/demo/test/gomonkey/ && GOARCH=amd64 go test -gcflags=all=-l -v -run=TestApplyFuncSeq -gcflags=-l
func TestApplyFuncSeq(t *testing.T) {
	Convey("Mock函数输出序列", t, func() {
		outputs := []gomonkey.OutputCell{
			{Values: gomonkey.Params{"a"}, Times: 1},
			{Values: gomonkey.Params{"b"}, Times: 2},
			{Values: gomonkey.Params{"c"}, Times: 3},
		}
		patches := gomonkey.ApplyFuncSeq(Hello, outputs)
		defer patches.Reset()

		So(Hello("tom"), ShouldEqual, "a")
		So(Hello("tom"), ShouldEqual, "b")
		So(Hello("tom"), ShouldEqual, "b")
		So(Hello("tom"), ShouldEqual, "c")
		So(Hello("tom"), ShouldEqual, "c")
		So(Hello("tom"), ShouldEqual, "c")
	})
}

// cd hi-golang/demo/test/gomonkey/ && GOARCH=amd64 go test -gcflags=all=-l -v -run=TestApplyMethodSeq
func TestApplyMethodSeq(t *testing.T) {
	Convey("Mock方法输出序列", t, func() {
		outputs := []gomonkey.OutputCell{
			{Values: gomonkey.Params{"a"}, Times: 1},
			{Values: gomonkey.Params{"b"}, Times: 2},
			{Values: gomonkey.Params{"c"}, Times: 3},
		}
		patches := gomonkey.ApplyMethodSeq(reflect.TypeOf(Service), "Hello", outputs)
		defer patches.Reset()

		So(Service.Hello("tom"), ShouldEqual, "a")
		So(Service.Hello("tom"), ShouldEqual, "b")
		So(Service.Hello("tom"), ShouldEqual, "b")
		So(Service.Hello("tom"), ShouldEqual, "c")
		So(Service.Hello("tom"), ShouldEqual, "c")
		So(Service.Hello("tom"), ShouldEqual, "c")
	})
}

// cd hi-golang/demo/test/gomonkey/ && GOARCH=amd64 go test -gcflags=all=-l -v -run=TestApplyFuncVarSeq
func TestApplyFuncVarSeq(t *testing.T) {
	Convey("Mock函数变量输出序列", t, func() {
		outputs := []gomonkey.OutputCell{
			{Values: gomonkey.Params{"a"}, Times: 1},
			{Values: gomonkey.Params{"b"}, Times: 2},
			{Values: gomonkey.Params{"c"}, Times: 3},
		}
		patches := gomonkey.ApplyFuncVarSeq(&Hey, outputs)
		defer patches.Reset()

		So(Hey("tom"), ShouldEqual, "a")
		So(Hey("tom"), ShouldEqual, "b")
		So(Hey("tom"), ShouldEqual, "b")
		So(Hey("tom"), ShouldEqual, "c")
		So(Hey("tom"), ShouldEqual, "c")
		So(Hey("tom"), ShouldEqual, "c")
	})
}

// cd hi-golang/demo/test/gomonkey/ && GOARCH=amd64 go test -gcflags=all=-l -v -run=TestPatchPair
func TestPatchPair(t *testing.T) {
	Convey("Mock多个函数", t, func() {
		patchPairs := [][2]interface{}{
			{
				Hello,
				func(_ string) string {
					return "abc"
				},
			},
			{
				Hi,
				func(_ string) string {
					return "abc"
				},
			},
		}
		patches := gomonkey.NewPatches()
		defer patches.Reset()
		for _, pair := range patchPairs {
			patches.ApplyFunc(pair[0], pair[1])
		}

		hello := Hello("Tom")
		So(hello, ShouldEqual, "abc")

		hi := Hi("Tom")
		So(hi, ShouldEqual, "abc")
	})
}
