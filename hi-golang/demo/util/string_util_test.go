package util

import (
	"testing"

	"github.com/smartystreets/goconvey/convey"
)

func TestToBytes(t *testing.T) {
	convey.Convey("TestToBytes", t, func() {
		a := ToBytes("a")
		convey.So(a, convey.ShouldNotBeNil)
	})
}

func TestToString(t *testing.T) {
	convey.Convey("TestToBytes", t, func() {
		a := ToString("a")
		convey.So(a, convey.ShouldNotBeNil)
	})
}

func TestParseInt(t *testing.T) {
	convey.Convey("TestParseInt", t, func() {
		parseInt := ParseInt("1")
		convey.So(parseInt, convey.ShouldEqual, 1)
	})
}
