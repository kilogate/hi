package main

import (
	"context"
	"fmt"
	"hi-golang/demo/util"
)

func main() {
	f(3)
}

func f(n int) {
	defer util.RecoverFromPanic(context.Background(), "f")
	fmt.Println(10 / n)
	f(n - 1)
}
