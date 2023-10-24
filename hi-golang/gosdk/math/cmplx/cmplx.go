package main

import (
	"fmt"
	"math/cmplx"
)

func main() {
	testComplex()
}

func testComplex() {
	// 求复数的平方根
	fmt.Println(cmplx.Sqrt(-1)) // "(0+1i)"
}
