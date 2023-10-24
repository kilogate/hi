package main

import (
	"fmt"
	"unicode/utf8"
)

func main() {
	testUTF8()
	testDecodeRuneInString()
}

func testUTF8() {
	s := "飞机666"
	fmt.Println(len(s))                    // 字节数：9
	fmt.Println(utf8.RuneCountInString(s)) // 字符数：5

	// UTF8编码
	s = "中国"
	fmt.Printf("% x\n", s) // e4 b8 ad e5 9b bd

	// UTF8编码 -> 码点：解码操作
	rs := []rune(s)
	fmt.Printf("%x\n", rs) // [4e2d 56fd]

	// 码点 -> UTF编码：编码操作
	ns := string(rs)
	fmt.Printf("% x\n", ns) // e4 b8 ad e5 9b bd

	// 错误的UTF8编码输出什么？
	fmt.Println("\uFFFD")        // �
	fmt.Println(string(1234567)) // �
}

func testDecodeRuneInString() {
	s := "hello 世界"

	// 手动UTF8解码
	for i := 0; i < len(s); {
		r, size := utf8.DecodeRuneInString(s[i:])
		fmt.Println(string(r))
		i += size
	}

	// range循环会自动做UTF8解码
	for _, r := range s {
		fmt.Println(string(r))
	}
}
