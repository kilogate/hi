package main

import (
	"flag"
	"fmt"
	"os"
)

var n = flag.Bool("n", false, "omit trailing newline")
var sep = flag.String("s", " ", "separator")

func main() {
	testParse()
}

func testParse() {
	fmt.Println(os.Args)     // [/private/var/folders/30/cmv9c_5j3mq_kthx63sb1t5c0000gn/T/GoLand/___go_build_flag_go -s / -n a b c]
	fmt.Println(flag.Args()) // []
	fmt.Println(*n)          // false
	fmt.Println(*sep)        // 空字符串

	flag.Parse()

	fmt.Println(os.Args)     // 不变
	fmt.Println(flag.Args()) // [a b c]
	fmt.Println(*n)          // true
	fmt.Println(*sep)        // /
}
