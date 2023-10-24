package main

import "fmt"

func main() {
	testComplex()
}

func testComplex() {
	// 使用complex函数构造复数
	var x = complex(1, 2) // 1+2i

	// 使用字面量构造复数
	y := 3 + 4i

	// 运算、实部、虚部
	fmt.Println(x * y)       // "(-5+10i)"
	fmt.Println(real(x * y)) // "-5"
	fmt.Println(imag(x * y)) // "10"

	// 实数部分可以省略
	fmt.Println(1i * 1i) // "(-1+0i)", i^2 = -1
}
