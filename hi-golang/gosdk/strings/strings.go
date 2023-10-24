package main

import (
	"fmt"
	"strings"
)

func main() {
	testStrings()
	testBuilder()
}

func testStrings() {
	// 子串数量
	s := "中原人中666"
	fmt.Println(strings.Count(s, ""))  // 8，注意，空子串返回原串字符数+1
	fmt.Println(strings.Count(s, "6")) // 3
	fmt.Println(strings.Count(s, "中")) // 2

	// 包含子串
	fmt.Println(strings.Contains(s, "原人"))     // true
	fmt.Println(strings.ContainsAny(s, "阿根廷")) // false
	fmt.Println(strings.ContainsAny(s, "中国"))  // true
	fmt.Println(strings.ContainsRune(s, '中'))  // true
	fmt.Println(strings.ContainsRune(s, '国'))  // fasle

	// Index/LastIndex
	fmt.Println(strings.Index(s, "中"))     // 0，注意这里是按字节数算的
	fmt.Println(strings.IndexAny(s, "好人")) // 6，注意这里是按字节数算的
	fmt.Println(strings.IndexByte(s, '6')) // 12，注意这里是按字节数算的
	fmt.Println(strings.IndexRune(s, '6')) // 12，注意这里是按字节数算的
	fmt.Println(strings.IndexFunc(s, func(r rune) bool {
		return r == '6'
	})) // 12，注意这里是按字节数算的
	fmt.Println(strings.LastIndex(s, "中")) // 9，注意这里是按字节数算的

	// 字符串分割
	s2 := "a,b,c,d,e,f"
	fmt.Println(strings.Split(s2, ","))             // [a b c d e f]
	fmt.Println(strings.SplitN(s2, ",", -1))        // [a b c d e f]
	fmt.Println(strings.SplitN(s2, ",", 0))         // []
	fmt.Println(strings.SplitN(s2, ",", 3))         // [a b c,d,e,f]
	fmt.Println(strings.SplitAfter(s2, ","))        // [a, b, c, d, e, f]
	fmt.Println(strings.SplitAfterN(s2, ",", 3))    // [a, b, c,d,e,f]
	fmt.Println(strings.Fields("a b\tc\nd  e   f")) // [a b c d e f]
	fmt.Println(strings.FieldsFunc("a b c d e f", func(r rune) bool {
		return r == ' '
	})) // [a b c d e f]

	// 字符串拼接
	fmt.Println(strings.Join([]string{"1", "2", "3"}, " "))

	// 前缀匹配/后缀匹配
	fmt.Println(strings.HasPrefix("abc", "a"))
	fmt.Println(strings.HasSuffix("abc", "c"))

	// Map
	mapRes := strings.Map(func(r rune) rune {
		return r + 1
	}, "12345")
	fmt.Println(mapRes)

	// Repeat
	fmt.Println(strings.Repeat("a", 3))

	// 大小写转换
	fmt.Println(strings.ToLower("Print"))
	fmt.Println(strings.ToUpper("Print"))

	// Trim
	trim := strings.Trim(" abc\t", " ")
	fmt.Println(trim)

	// Replace
	fmt.Println(strings.Replace("1,2,3,4,5", ",", "/", 3))
	fmt.Println(strings.ReplaceAll("1,2,3,4,5", ",", "/"))

	// EqualFold
	fmt.Println(strings.EqualFold("Lask", "LASK"))

	// Cut
	fmt.Println(strings.Cut("abc/def/ghi", "/"))
}

func testBuilder() {
	var b strings.Builder
	b.Grow(3)
	b.WriteString("1")
	b.WriteString("2")
	b.WriteString("3")
	s := b.String()
	fmt.Println(s)
}
