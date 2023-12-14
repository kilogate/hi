package main

import (
	"context"
	"testing"

	"github.com/smartystreets/goconvey/convey"
)

func Test_readFromCSV(t *testing.T) {
	convey.Convey("Test_readFromCSV", t, func() {
		err := readFromCSV(context.Background())
		convey.So(err, convey.ShouldBeNil)
	})
}

func Test_writeToCSV(t *testing.T) {
	convey.Convey("Test_writeToCSV", t, func() {
		err := writeToCSV(context.Background())
		convey.So(err, convey.ShouldBeNil)
	})
}
