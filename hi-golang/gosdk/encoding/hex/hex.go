package main

import (
	"encoding/hex"
	"fmt"
)

func main() {
	testEncoding()
}

func testEncoding() {
	msg := "Hello? 世界"
	encoded := hex.EncodeToString([]byte(msg))
	fmt.Println(encoded)

	decoded, err := hex.DecodeString(encoded)
	if err != nil {
		fmt.Println("decode error:", err)
		return
	}
	fmt.Println(string(decoded))
}
