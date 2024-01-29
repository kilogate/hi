package main

import (
	"fmt"
	"time"

	"github.com/samber/lo"
)

func main() {
	testPtr()
	testEmpty()
}

func testPtr() {
	// 值 -> 指针
	toPtr := lo.ToPtr(time.Now())
	fmt.Println(toPtr)

	// 指针 -> 值
	fromPtr := lo.FromPtr(toPtr)
	fmt.Println(fromPtr)
}

func testEmpty() {
	var empty time.Time

	// 是否零值
	isEmpty := lo.IsEmpty(empty)
	fmt.Println(isEmpty)

	// 是否非零值
	isNotEmpty := lo.IsNotEmpty(empty)
	fmt.Println(isNotEmpty)
}
