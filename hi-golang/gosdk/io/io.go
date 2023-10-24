package main

import (
	"fmt"
	"io"
	"log"
	"net/http"
	"os"
)

func main() {
	testReadAll()
	testCopy()
	testDiscard()
}

func testReadAll() {
	resp, err := http.Get("https://www.baidu.com")
	if err != nil {
		log.Fatalf("Get err, err: %+v\n", err)
		return
	}
	defer resp.Body.Close()

	// 一次性读取全部内容
	respBodyBytes, err := io.ReadAll(resp.Body)
	if err != nil {
		log.Fatalf("ReadlAll err, err: %+v\n", err)
		return
	}
	fmt.Println(string(respBodyBytes))
}

func testCopy() {
	resp, err := http.Get("https://www.baidu.com")
	if err != nil {
		log.Fatalf("Get err, err: %+v\n", err)
		return
	}
	defer resp.Body.Close()

	// 避免申请缓冲区：直接复制没有中间商
	written, err := io.Copy(os.Stdout, resp.Body)
	if err != nil {
		log.Fatalf("Copy err, err: %+v\n", err)
		return
	}
	fmt.Printf("\n\nwritten: %v\n", written)
}

func testDiscard() {
	resp, err := http.Get("https://www.baidu.com")
	if err != nil {
		log.Fatalf("Get err, err: %+v\n", err)
		return
	}
	defer resp.Body.Close()

	// io.Discard
	written, err := io.Copy(io.Discard, resp.Body)
	if err != nil {
		log.Fatalf("Copy err, err: %+v\n", err)
		return
	}
	fmt.Printf("\n\nwritten: %v\n", written)
}
