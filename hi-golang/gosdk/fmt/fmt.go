package main

import (
	"fmt"
	"math"
	"os"
)

type Stu struct {
	Name string
	Age  int
}

type User struct {
	NO   int
	Name string
}

func (u *User) Format(s fmt.State, verb rune) {
	switch verb {
	case 'v':
		fmt.Fprintf(s, "vvv[%d]%s", u.NO, u.Name)
	case 's':
		fmt.Fprintf(s, "sss[%d]%s", u.NO, u.Name)
	}
}

func main() {
	testPrintln()
	testPrintf()
	testSprintf()
	testFprintf()
	testCustomFormat()
	testScanf()
}

func testPrintln() {
	fmt.Println("abc")
}

// testPrintf 格式化动词和转义字符
func testPrintf() {
	// %x, %d, %o, %b ：十六进制整数，十进制整数，八进制整数，二进制整数
	fmt.Printf("%x, %d, %o, %b\n", 13, 13, 13, 13)

	fmt.Printf("%+d\n", 13) // 带符号
	fmt.Printf("%#o\n", 13) // 带零前导
	fmt.Printf("%X\n", 13)  // 大写十六进制
	fmt.Printf("%#x\n", 13) // 带0x

	fmt.Printf("%5d\n", 13)  // 长度5，右对齐
	fmt.Printf("%-5d\n", 13) // 长度5，左对齐
	fmt.Printf("%05d\n", 13) // 长度5，左边补零

	fmt.Printf("%b %[1]o %[1]d %[1]x\n", 13) // [1] 表示继续使用第一个参数

	// %f, %g, %e
	fmt.Printf("%f, %g, %e\n", math.Pi, math.Pi, math.Pi) // 3.141593, 3.141592653589793, 3.141593e+00

	// 宽度.精度
	fmt.Printf("%.2f\n", 111.222)    // 两位小数
	fmt.Printf("%4.2f\n", 111.222)   // 两位小数，最小宽度4
	fmt.Printf("%010.2f\n", 111.222) // 两位小数，最小宽度10，不足补零

	// %t：布尔值，true或false
	fmt.Printf("%t, %t\n", true, false)

	// %c：字符（rune） (Unicode码点)
	fmt.Printf("%c, %c, %c\n", 'a', 'b', 22269)

	// %s：字符串
	fmt.Printf("%s\n", "字符串")
	fmt.Printf("%5s\n", "字符串")         // 展示的最小宽度为5，右对齐
	fmt.Printf("%*s\n", 5, "字符串")      // 展示的最小宽度为5，右对齐
	fmt.Printf("%-5s\n", "字符串")        // 左对齐
	fmt.Printf("%05s\n", "字符串")        // 补零
	fmt.Printf("%.5s\n", "123456789")  // 截断的最大宽度5
	fmt.Printf("%5.7s\n", "123456789") // 展示的最小宽度为5，截断的最大宽度7
	fmt.Printf("%5.3s\n", "123456789") // 展示的最小宽度为5，截断的最大宽度3

	// %q：带双引号的字符串"abc"或带单引号的字符'd'
	fmt.Printf("带双引号的字符串%q或带单引号的字符%q\n", "abc", 'd')
	fmt.Printf("%q\n", `a"b"c`)  // 引号被转义
	fmt.Printf("%#q\n", `a"b"c`) // 反引号

	// %U：Unicode
	fmt.Printf("%d %X\n", '中', '中')
	fmt.Printf("%U\n", '中')
	fmt.Printf("%#U\n", '中') // 带字符

	// %v：变量的自然形式（natural format），打印结构体使用
	fmt.Printf("%v, %v, %v, %v, %v\n", 13, math.Pi, true, 'a', "abc")

	stu := &Stu{"Lask", 30}
	fmt.Printf("%v\n", stu)  // 仅打印值
	fmt.Printf("%+v\n", stu) // 再加上字段名
	fmt.Printf("%#v\n", stu) // 再加上包名和类型名

	// %p：指针
	fmt.Printf("%p\n", stu)
	fmt.Printf("%#p\n", stu) // 不带0x

	// %T：变量的类型
	fmt.Printf("%T, %T, %T, %T, %T\n", 13, math.Pi, true, 'a', "abc")

	// %%：百分号
	fmt.Printf("%%\n")

	// \n：换行符、\t：制表符
	fmt.Printf("a\tb\n")
}

func testSprintf() {
	// 格式化并返回字符串
	str := fmt.Sprintf("%s", "abc")
	fmt.Println(str)
}

func testFprintf() {
	n, err := fmt.Fprintf(os.Stdout, "abc\n")
	fmt.Println(n)
	fmt.Println(err)
}

func testCustomFormat() {
	u := &User{
		NO:   123,
		Name: "Tom",
	}
	s := fmt.Sprintf("%s", u) // sss[123]Tom
	v := fmt.Sprintf("%v", u) // vvv[123]Tom
	fmt.Println(s, v)
}

func testScanf() {
	// 声明变量
	var stringValue string
	var intValue int
	var floatValue float32
	var boolValue bool

	// 使用连续的空格分隔输入，将值绑定到对应类型的变量
	fmt.Scanf("%s", &stringValue)
	fmt.Scanf("%d", &intValue)
	fmt.Scanf("%g", &floatValue)
	fmt.Scanf("%t", &boolValue)

	// 输入"str 123 1.23 true"
	fmt.Println(stringValue, intValue, floatValue, boolValue)
}
