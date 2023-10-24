package main

import "fmt"

func main() {
	testOverflow()
	testMod()
	testBit()
}

// 溢出
func testOverflow() {
	// 无符号数计算溢出
	var u uint8 = 255
	fmt.Println(u, u+1, u*u) // "255 0 1"

	// 有符号数计算溢出
	var i int8 = 127
	fmt.Println(i, i+1, i*i) // "127 -128 1"
}

// 取模
func testMod() {
	// 取模运算符的符号和被取模数的符号总是一致的
	fmt.Println(5 % 3)   // 2
	fmt.Println(5 % -3)  // 2
	fmt.Println(-5 % 3)  // -2
	fmt.Println(-5 % -3) // -2

	// 整数除法会截断余数
	fmt.Println(9 / 5.0) // 1.8，浮点数除法
	fmt.Println(9 / 5)   // 1，整数除法
}

// 位运算
func testBit() {
	var x uint8 = 1<<1 | 1<<5
	var y uint8 = 1<<1 | 1<<2

	fmt.Printf("%08b\n", x) // "00100010", the set {1, 5}
	fmt.Printf("%08b\n", y) // "00000110", the set {1, 2}

	fmt.Printf("%08b\n", x&y)  // "00000010", the intersection {1}, 交集
	fmt.Printf("%08b\n", x|y)  // "00100110", the union {1, 2, 5}, 并集
	fmt.Printf("%08b\n", x^y)  // "00100100", the symmetric difference {2, 5}, 对称差集
	fmt.Printf("%08b\n", ^y)   // "11111001", 取反
	fmt.Printf("%08b\n", x&^y) // "00100000", the difference {5}, 按位置零
	fmt.Printf("%08b\n", y&^x) // "00000100", the difference {2}, 按位置零

	for i := uint(0); i < 8; i++ {
		if x&(1<<i) != 0 { // membership test
			fmt.Println(i) // "1", "5"
		}
	}

	fmt.Printf("%08b\n", x<<1) // "01000100", the set {2, 6}, 左移用零填充右边空缺
	fmt.Printf("%08b\n", x>>1) // "00010001", the set {0, 4}, 无符号数右移用零填充左边空缺, 有符号数右移用符号位填充左边空缺
}
