package main

import (
	"encoding/base64"
	"fmt"
)

func main() {
	testStdEncoding()
	testURLEncoding()
}

func testStdEncoding() {
	msg := "Hello? 世界"
	encoded := base64.StdEncoding.EncodeToString([]byte(msg))
	fmt.Println(encoded)

	decoded, err := base64.StdEncoding.DecodeString(encoded)
	if err != nil {
		fmt.Println("decode error:", err)
		return
	}
	fmt.Println(string(decoded))
}

func testURLEncoding() {
	msg := "Hello? 世界"
	encoded := base64.URLEncoding.EncodeToString([]byte(msg))
	fmt.Println(encoded)

	decoded, err := base64.URLEncoding.DecodeString(encoded)
	if err != nil {
		fmt.Println("decode error:", err)
		return
	}
	fmt.Println(string(decoded))
}
