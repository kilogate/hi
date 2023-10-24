package main

import (
	"fmt"
	"log"
)

func main() {
	//testPrintf()
	//testPanicf()
	testFatalf()

	fmt.Println("main end")
}

func testPrintf() {
	// Printf 打印日志
	log.Printf("ABC\n")
	fmt.Println("testPrintf end")
}

func testPanicf() {
	defer func() {
		if p := recover(); p != nil {
			fmt.Printf("recover: %+v", p)
		}
	}()

	// Panicf 打印日志并panic，可以被recover恢复
	log.Panicf("ABC\n")
	fmt.Println("testPanicf end")
}

func testFatalf() {
	defer func() {
		if p := recover(); p != nil {
			fmt.Printf("recover: %+v", p)
		}
	}()

	// Fatalf 打印日志并退出，不能被recover恢复
	log.Fatalf("ABC\n")
	fmt.Println("testFatalf end")
}
