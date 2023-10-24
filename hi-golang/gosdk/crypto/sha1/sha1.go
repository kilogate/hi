package main

import (
	"crypto/sha1"
	"encoding/hex"
	"fmt"
)

func main() {
	res := sha1AndHex("hello, world")
	fmt.Println(res)
}

func sha1AndHex(data string) string {
	sum := sha1.Sum([]byte(data))
	return hex.EncodeToString(sum[:])
}
