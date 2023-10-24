package main

import (
	"fmt"
	"math"
)

func main() {
	testConst()
	testInfAndNan()
	testSin()
}

func testConst() {
	fmt.Println(math.E, math.Pi, math.Phi)

	fmt.Println(math.MinInt, math.MinInt8, math.MinInt16, math.MinInt32, math.MinInt64)
	fmt.Println(math.MaxInt, math.MaxInt8, math.MaxInt16, math.MaxInt32, math.MaxInt64)

	fmt.Println(math.MaxFloat32, math.MaxFloat64)
	fmt.Println(math.SmallestNonzeroFloat32, math.SmallestNonzeroFloat64)
}

func testInfAndNan() {
	var z float64
	fmt.Println(z == 0)               // 0
	fmt.Println(-z == -0)             // -0
	fmt.Println(math.IsInf(1/z, 1))   // +Inf
	fmt.Println(math.IsInf(-1/z, -1)) // -Inf
	fmt.Println(math.IsNaN(z / z))    // NaN

	nan := math.NaN()
	fmt.Println(nan == nan, nan < nan, nan > nan) // "false false false"
}

func testSin() {
	var sin30, cos30 = math.Sin(30), math.Cos(30) // sin(30°), cos(30°)
	fmt.Println(sin30, cos30)
}
