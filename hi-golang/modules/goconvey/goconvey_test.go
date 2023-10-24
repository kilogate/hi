package goconvey

import (
	"testing"

	"github.com/smartystreets/goconvey/convey"
)

// cd /Users/bytedance/IdeaProjects/hello/modules/github.com/smartystreets/goconvey/convey/ && go test -v -run=TestNewStudent
func TestNewStudent(t *testing.T) {
	convey.Convey("正常的测试用例", t, func() {
		stu, err := NewStudent(123, "Tom")
		convey.Convey("没有异常", func() {
			convey.So(err, convey.ShouldBeNil)
		})
		convey.Convey("返回正常值", func() {
			convey.So(stu, convey.ShouldResemble, &Student{
				NO:      123,
				Name:    "Tom",
				Chinese: 0,
				English: 0,
				Math:    0,
			})
		})
	})

	convey.Convey("异常的测试用例", t, func() {
		stu, err := NewStudent(-1, "")
		convey.Convey("有异常", func() {
			convey.So(err, convey.ShouldBeError)
		})
		convey.Convey("返回空值", func() {
			convey.So(stu, convey.ShouldBeNil)
		})
	})
}

// cd /Users/bytedance/IdeaProjects/hello/modules/github.com/smartystreets/goconvey/convey/ && go test -v -run=TestStudent_GetAverage
func TestStudent_GetAverage(t *testing.T) {
	convey.Convey("正常的测试用例", t, func() {
		stu := &Student{
			NO:      123,
			Name:    "Tom",
			Chinese: 12,
			English: 13,
			Math:    14,
		}
		avg, err := stu.GetAverage()
		convey.Convey("没有异常", func() {
			convey.So(err, convey.ShouldBeNil)
		})
		convey.Convey("返回正常值", func() {
			convey.So(avg, convey.ShouldEqual, 13)
		})
	})

	convey.Convey("异常的测试用例", t, func() {
		stu := &Student{
			NO:      123,
			Name:    "Tom",
			Chinese: 0,
			English: 0,
			Math:    0,
		}
		avg, err := stu.GetAverage()
		convey.Convey("有异常", func() {
			convey.So(err, convey.ShouldBeError)
		})
		convey.Convey("返回零值", func() {
			convey.So(avg, convey.ShouldEqual, 0)
		})
	})
}
