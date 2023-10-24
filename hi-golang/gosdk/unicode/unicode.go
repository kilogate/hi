package main

import (
	"fmt"
	"unicode"
)

func main() {
	isDigit := unicode.IsDigit('2')
	isLetter := unicode.IsLetter('a')
	isUpper := unicode.IsUpper('A')
	upper := unicode.ToUpper('a')
	fmt.Println(isDigit, isLetter, isUpper, upper)
}
