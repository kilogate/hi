package mockey

import (
	"fmt"
	"hi-golang/modules/mockey/other"
	"testing"

	"github.com/bytedance/mockey"
	"github.com/smartystreets/goconvey/convey"
)

func TestMockFunc(t *testing.T) {
	convey.Convey("TestMockFunc", t, func() {
		mockey.Mock(Foo).Return("c").Build()

		res := Foo("a")
		convey.So(res, convey.ShouldEqual, "c")
	})
}

func TestMockMethod(t *testing.T) {
	convey.Convey("TestMockMethod", t, func() {
		mockey.Mock((*A).Foo).Return("c").Build()

		res := new(A).Foo("b")
		convey.So(res, convey.ShouldEqual, "c")
	})
}

func TestMockValue(t *testing.T) {
	convey.Convey("TestMockXXX", t, func() {
		mockey.MockValue(&Bar).To(1)

		res := Bar
		convey.So(res, convey.ShouldEqual, 1)
	})
}

func TestWhen(t *testing.T) {
	convey.Convey("TestWhen", t, func() {
		convey.Convey("TestWhen for func", func() {
			mockey.Mock(Foo).
				When(func(in string) bool {
					return in == "x"
				}).
				Return("XXX").
				Build()

			res := Foo("a")
			convey.So(res, convey.ShouldEqual, "a")

			res = Foo("x")
			convey.So(res, convey.ShouldEqual, "XXX")
		})

		convey.Convey("TestWhen for method", func() {
			mockey.Mock((*A).Foo).
				When(func(self *A, in string) bool {
					return self != nil && in == "x"
				}).
				Return("XXX").
				Build()

			res := new(A).Foo("a")
			convey.So(res, convey.ShouldEqual, "a")

			res = new(A).Foo("x")
			convey.So(res, convey.ShouldEqual, "XXX")
		})
	})
}

func TestReturnSequence(t *testing.T) {
	convey.Convey("TestReturnSequence", t, func() {
		mockey.Mock(Foo).Return(mockey.Sequence("A").Then("B").Times(2).Then("C").Times(3)).Build()

		res := Foo("a")
		convey.So(res, convey.ShouldEqual, "A")

		res = Foo("a")
		convey.So(res, convey.ShouldEqual, "B")

		res = Foo("a")
		convey.So(res, convey.ShouldEqual, "B")

		res = Foo("a")
		convey.So(res, convey.ShouldEqual, "C")

		res = Foo("a")
		convey.So(res, convey.ShouldEqual, "C")

		res = Foo("a")
		convey.So(res, convey.ShouldEqual, "C")
	})
}

func TestTo(t *testing.T) {
	convey.Convey("TestTo for func", t, func() {
		hook := func(in string) string {
			if len(in) > 3 {
				return in
			}
			return "Hello" + in
		}
		mockey.Mock(Foo).To(hook).Build()

		res := Foo("a")
		convey.So(res, convey.ShouldEqual, "Helloa")

		res = Foo("Hello")
		convey.So(res, convey.ShouldEqual, "Hello")
	})

	convey.Convey("TestTo for method", t, func() {
		hook := func(self *A, in string) string {
			if self == nil {
				return "null"
			}
			if len(in) > 3 {
				return in
			}
			return "Hello" + in
		}
		mockey.Mock((*A).Foo).To(hook).Build()

		a := new(A)
		res := a.Foo("a")
		convey.So(res, convey.ShouldEqual, "Helloa")

		res = a.Foo("Hello")
		convey.So(res, convey.ShouldEqual, "Hello")
	})
}

func TestOrigin(t *testing.T) {
	convey.Convey("TestOrigin", t, func() {
		origin := Foo
		mockey.Mock(Foo).Return("XXX").Origin(&origin).Build()

		res := Foo("a")
		convey.So(res, convey.ShouldEqual, "XXX")

		res = origin("a")
		convey.So(res, convey.ShouldEqual, "a")
	})
}

func TestMocker(t *testing.T) {
	convey.Convey("TestMocker", t, func() {
		mocker := mockey.Mock(Foo).
			When(func(in string) bool {
				return in == "x"
			}).
			Return("XXX").
			Build()

		res := Foo("a")
		convey.So(res, convey.ShouldEqual, "a")

		res = Foo("x")
		convey.So(res, convey.ShouldEqual, "XXX")

		times := mocker.Times()
		convey.So(times, convey.ShouldEqual, 2)

		mockTimes := mocker.MockTimes()
		convey.So(mockTimes, convey.ShouldEqual, 1)
	})
}

func TestPatch(t *testing.T) {
	convey.Convey("TestPatch", t, func() {
		mocker := mockey.Mock(Foo).Return("XXX").Build()

		res := Foo("a")
		convey.So(res, convey.ShouldEqual, "XXX")

		mocker.UnPatch()
		res = Foo("a")
		convey.So(res, convey.ShouldEqual, "a")

		mocker.Patch()
		res = Foo("a")
		convey.So(res, convey.ShouldEqual, "XXX")
	})
}

func TestGetPrivateMethod(t *testing.T) {
	convey.Convey("TestGetPrivateMethod", t, func() {
		o := other.NewOther()
		method := mockey.GetMethod(o, "Foo")
		mockey.Mock(method).Return("X").Build()

		res := o.Foo("a")
		convey.So(res, convey.ShouldEqual, "X")
	})
}

func TestGetGoroutineId(t *testing.T) {
	convey.Convey("TestGetGoroutineId", t, func() {
		id := mockey.GetGoroutineId()
		convey.So(id, convey.ShouldNotBeEmpty)
	})
}

func TestPatchConvey(t *testing.T) {
	mockey.PatchConvey("TestPatchConvey", t, func() {
		mockey.Mock(Foo).Return("c").Build()

		res := Foo("a")
		convey.So(res, convey.ShouldEqual, "c")
	})

	// `PatchConvey`外自动释放mock
	res := Foo("a")
	fmt.Println(res) // a
}
