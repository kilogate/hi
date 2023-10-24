package main

import (
	"fmt"
	"math/rand"
	"time"
)

func main() {
	r := rand.New(rand.NewSource(time.Now().UTC().UnixNano()))
	for i := 0; i < 1000; i++ {
		fmt.Println(r.Intn(10))
	}
}
