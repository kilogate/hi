package main

import (
	"encoding/base32"
	"fmt"
)

func main() {
	testStdEncoding()
	testHexEncoding()
}

func testStdEncoding() {
	msg := "Hello? 世界"
	encoded := base32.StdEncoding.EncodeToString([]byte(msg))
	fmt.Println(encoded)

	decoded, err := base32.StdEncoding.DecodeString(encoded)
	if err != nil {
		fmt.Println("decode error:", err)
		return
	}
	fmt.Println(string(decoded))
}

func testHexEncoding() {
	msg := "Hello? 世界"
	encoded := base32.HexEncoding.EncodeToString([]byte(msg))
	fmt.Println(encoded)

	decoded, err := base32.HexEncoding.DecodeString(encoded)
	if err != nil {
		fmt.Println("decode error:", err)
		return
	}
	fmt.Println(string(decoded))
}
