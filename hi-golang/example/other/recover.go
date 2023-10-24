package main

import (
	"fmt"
	"log"
	"runtime/debug"
)

func main() {
	f(3)
}

func f(n int) {
	defer RecoverFromPanicFunc("f")
	fmt.Println(10 / n)
	f(n - 1)
}

var RecoverFromPanicFunc = func(msg string) {
	if p := recover(); p != nil {
		log.Printf("goroutine occurs panic, msg: %s, panic: %+v\n%+v", msg, p, string(debug.Stack()))
	}
}
