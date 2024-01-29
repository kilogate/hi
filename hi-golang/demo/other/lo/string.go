package main

import (
	"fmt"

	"github.com/samber/lo"
)

func main() {
	// 小写字母字符集
	fmt.Println(string(lo.LowerCaseLettersCharset))
	// 大写字母字符集
	fmt.Println(string(lo.UpperCaseLettersCharset))
	// 字母字符集
	fmt.Println(string(lo.LettersCharset))
	// 数字字符集
	fmt.Println(string(lo.NumbersCharset))
	// 字母和数字字符集
	fmt.Println(string(lo.AlphanumericCharset))
	// 特殊字符字符集
	fmt.Println(string(lo.SpecialCharset))
	// 所有字符字符集
	fmt.Println(string(lo.AllCharset))

	// 随机字符串
	randomString := lo.RandomString(10, lo.LettersCharset)
	fmt.Println(randomString)

	// 子串
	substring := lo.Substring("abc一二三四", 1, 4) // cde
	fmt.Println(substring)

	// 字符串分块
	chunkString := lo.ChunkString("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 5) // [ABCDE FGHIJ KLMNO PQRST UVWXY Z]
	fmt.Println(chunkString)

	// 字符数
	runeLength := lo.RuneLength(string(lo.LowerCaseLettersCharset)) // 26
	fmt.Println(runeLength)
}
