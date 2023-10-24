package main

import (
	"crypto/sha256"
	"encoding/hex"
	"fmt"
)

func main() {
	res := sha256AndHex("hello, world")
	fmt.Println(res)
}

func sha256AndHex(data string) string {
	sum := sha256.Sum256([]byte(data))
	return hex.EncodeToString(sum[:])
}
