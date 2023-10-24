package main

import (
	"fmt"
	"io"
	"io/ioutil"
	"log"
	"net/http"
)

func main() {
	testReadAll()
	testDiscard()
}

func testReadAll() {
	resp, err := http.Get("https://www.baidu.com")
	if err != nil {
		log.Fatalf("Get err, err: %+v\n", err)
		return
	}
	defer resp.Body.Close()

	// 一次性读取全部内容（io.ReadAll）
	respBodyBytes, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		log.Fatalf("ReadlAll err, err: %+v\n", err)
		return
	}
	fmt.Println(string(respBodyBytes))
}

func testDiscard() {
	resp, err := http.Get("https://www.baidu.com")
	if err != nil {
		log.Fatalf("Get err, err: %+v\n", err)
		return
	}
	defer resp.Body.Close()

	// ioutil.Discard（io.Discard）
	written, err := io.Copy(ioutil.Discard, resp.Body)
	if err != nil {
		log.Fatalf("Copy err, err: %+v\n", err)
		return
	}
	fmt.Printf("\n\nwritten: %v\n", written)
}
