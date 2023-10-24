package main

import (
	"crypto/md5"
	"encoding/hex"
	"fmt"
)

func main() {
	res := md5AndHex("Hello, world!")
	fmt.Println(res)
}

func md5AndHex(data string) string {
	sum := md5.Sum([]byte(data))
	return hex.EncodeToString(sum[:])
}
