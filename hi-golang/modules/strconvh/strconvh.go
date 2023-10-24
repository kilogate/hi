package main

import (
	"fmt"
	"math"

	"github.com/apaxa-go/helper/strconvh"
)

func main() {
	testFormat()
	testParse()
}

func testFormat() {
	formatInt := strconvh.FormatInt(123)
	formatInt8 := strconvh.FormatInt8(123)
	formatInt16 := strconvh.FormatInt16(123)
	formatInt32 := strconvh.FormatInt32(123)
	formatInt64 := strconvh.FormatInt64(123)
	fmt.Println(formatInt, formatInt8, formatInt16, formatInt32, formatInt64)

	formatUint := strconvh.FormatUint(123)
	formatUint8 := strconvh.FormatUint8(123)
	formatUint16 := strconvh.FormatUint16(123)
	formatUint32 := strconvh.FormatUint32(123)
	formatUint64 := strconvh.FormatUint64(123)
	fmt.Println(formatUint, formatUint8, formatUint16, formatUint32, formatUint64)

	formatFloat32 := strconvh.FormatFloat32(math.Pi)
	formatFloat64 := strconvh.FormatFloat64(math.Pi)
	formatFloat32Prec := strconvh.FormatFloat32Prec(math.Pi, 2)
	formatFloat64Prec := strconvh.FormatFloat64Prec(math.Pi, 2)
	fmt.Println(formatFloat32, formatFloat64, formatFloat32Prec, formatFloat64Prec)
}

func testParse() {
	parseInt, err := strconvh.ParseInt("123")
	parseInt8, err := strconvh.ParseInt8("123")
	parseInt16, err := strconvh.ParseInt16("123")
	parseInt32, err := strconvh.ParseInt32("123")
	parseInt64, err := strconvh.ParseInt64("123")
	fmt.Println(parseInt, parseInt8, parseInt16, parseInt32, parseInt64, err)

	parseUint, err := strconvh.ParseUint("123")
	parseUint8, err := strconvh.ParseUint8("123")
	parseUint16, err := strconvh.ParseUint16("123")
	parseUint32, err := strconvh.ParseUint32("123")
	parseUint64, err := strconvh.ParseUint64("123")
	fmt.Println(parseUint, parseUint8, parseUint16, parseUint32, parseUint64)

	parseFloat32, err := strconvh.ParseFloat32("3.14159265354")
	parseFloat64, err := strconvh.ParseFloat64("3.14159265354")
	fmt.Println(parseFloat32, parseFloat64)
}
