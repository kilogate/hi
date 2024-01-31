package main

import (
	"fmt"

	"hi-golang/demo/util"
)

func main() {
	for i := 0; i < 100; i++ {
		fmt.Println(util.NewUUID())
	}
}
