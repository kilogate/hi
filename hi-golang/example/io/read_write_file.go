package main

import (
	"fmt"
	"io"
	"io/ioutil"
	"os"
)

func main() {
	testReadFile()
	testWriteFile()
	testWriteString()
}

func testReadFile() {
	filename := "/Users/bytedance/opt/work/sublimetext/tmp0.txt"
	content, err := ioutil.ReadFile(filename)
	fmt.Println(string(content), err)
}

func testWriteFile() {
	filename := "/Users/bytedance/opt/work/sublimetext/tmp0.txt"
	data := "111"
	err := ioutil.WriteFile(filename, []byte(data), 0666)
	fmt.Println(err)
}

func testWriteString() {
	filename := "/Users/bytedance/Downloads/c.txt"
	data := "111"
	file, err := os.OpenFile(filename, os.O_CREATE|os.O_APPEND|os.O_RDWR, 0666)
	n, err := io.WriteString(file, data)
	fmt.Println(n, err)
}
