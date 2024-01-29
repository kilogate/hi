package main

import (
	"fmt"

	"github.com/samber/lo"
)

func main() {
	// 三目运算符
	ternary := lo.Ternary(5 > 3, "if", "else")
	fmt.Println(ternary)

	// if else
	ifElse := lo.If(5 > 8, "if").ElseIf(6 > 8, "else if").Else("else")
	fmt.Println(ifElse)

	// switch
	s := "F"
	r := lo.Switch[string, string](s).
		Case("A", "is A").
		Case("B", "is B").
		Default("not match")
	fmt.Println(r)
}
