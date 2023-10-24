package main

import "fmt"

func main() {
	fmt.Println(gcd1(12, 18))
	fmt.Println(gcd1(18, 12))
	fmt.Println(gcd1(24, 12))
}

// 计算两个整数值的最大公约数，欧几里得算法
func gcd1(x, y int) int {
	for y != 0 {
		t := x % y
		x = y
		y = t
	}

	return x
}

// 计算两个整数值的最大公约数，欧几里得算法
func gcd2(x, y int) int {
	for y != 0 {
		x, y = y, x%y
	}

	return x
}
