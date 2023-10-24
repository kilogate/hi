package main

import (
	"fmt"

	"github.com/apaxa-go/helper/strconvh"
)

func main() {
	// rune 就是 Unicode 的码点
	r := '中'        // int32 类型，值为 20013
	sr := string(r) // string 类型，值为 "中"
	fmt.Println(r, sr)

	var n int32 = 20013           // int32 类型，值为 20013
	sn := string(n)               // string 类型，值为 "中"
	fn := strconvh.FormatInt32(n) // string 类型，值为 "20013"
	fmt.Println(n, sn, fn)

	i := 20013                  // int 类型，值为 20013
	si := string(i)             // string 类型，值为 "中"
	fi := strconvh.FormatInt(i) // string 类型，值为 "20013"
	fmt.Println(i, si, fi)

	// 看下前256个码点都是啥
	for i := 0; i < 256; i++ {
		fmt.Printf("%d: %s\n", i, string(i))
	}
}
