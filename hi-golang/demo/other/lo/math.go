package main

import (
	"fmt"

	"github.com/samber/lo"
)

func main() {
	// 产生数字序列
	fmt.Println(lo.RangeWithSteps(2, 5, 1))    // [2 3 4]
	fmt.Println(lo.RangeWithSteps(3, 10, 3))   // [3 6 9]
	fmt.Println(lo.RangeWithSteps(-10, -3, 3)) // [-10 -7 -4]

	// 将数字夹在上下边界内
	fmt.Println(lo.Clamp(-5, 1, 10)) // 1
	fmt.Println(lo.Clamp(5, 1, 10))  // 5
	fmt.Println(lo.Clamp(50, 1, 10)) // 10
}
