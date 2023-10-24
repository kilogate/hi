package main

import (
	"crypto/sha512"
	"encoding/hex"
	"fmt"
)

func main() {
	res := sha512AndHex("hello, world")
	fmt.Println(res)
}

func sha512AndHex(data string) string {
	sum := sha512.Sum512([]byte(data))
	return hex.EncodeToString(sum[:])
}
