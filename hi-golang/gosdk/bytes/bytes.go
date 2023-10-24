package main

import (
	"bytes"
	"fmt"
)

func main() {
	testBytes()
	testBuffer()
}

func testBytes() {
	fmt.Println(bytes.Contains([]byte{'a', 'b', 'c'}, []byte{'a', 'b'}))             // true
	fmt.Println(bytes.Count([]byte{'a', 'b', 'c', 'a', 'b', 'c', 'a'}, []byte{'a'})) // 3
	fmt.Println(bytes.Fields([]byte{'a', 'b', ' ', 'c'}))                            // [[97 98] [99]]
	fmt.Println(bytes.HasPrefix([]byte{'a', 'b', 'c'}, []byte{'a', 'b'}))            // true
	fmt.Println(bytes.HasSuffix([]byte{'a', 'b', 'c'}, []byte{'a', 'b'}))            // false
	fmt.Println(bytes.Index([]byte{'a', 'b', 'c'}, []byte{'a', 'b'}))                // 0
	fmt.Println(bytes.Join([][]byte{{'a'}, {'a'}, {'a'}}, []byte{','}))              // [97 44 97 44 97]
	fmt.Println(bytes.Repeat([]byte{'a', 'b', 'c'}, 3))                              // [97 98 99 97 98 99 97 98 99]
}

func testBuffer() {
	var buf bytes.Buffer

	buf.WriteByte('1')    // 只能写单字节的字符（单字节兼容UTF8编码，可以直接写入）
	buf.WriteString(", ") // 写字符串（本身就是UTF8编码的）
	buf.WriteRune('二')    // 写任意字符（使用UTF8编码后再写入）
	buf.WriteString(", ")
	fmt.Fprintf(&buf, "%d", 3) // 这里应该也有UTF8编码
	s := buf.String()

	fmt.Println(s)
}
