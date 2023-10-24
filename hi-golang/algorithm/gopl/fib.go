package main

import "fmt"

func main() {
	fmt.Println(fib1(5))
	fmt.Println(fib2(5))
}

// 菲波那切数列，递归法
func fib1(n int) int {
	switch n {
	case 0:
		return 0
	case 1:
		return 1
	default:
		return fib1(n-1) + fib1(n-2)
	}
}

// 菲波那切数列，循环法
func fib2(n int) int {
	x, y := 0, 1
	for i := 0; i < n; i++ {
		x, y = y, x+y
	}
	return x
}
