package main

import (
	"fmt"

	"github.com/samber/lo"
)

func equals[T comparable](i, j T) bool {
	return i == j
}

func main() {
	var b bool = true
	fmt.Println(b)

	var u8 uint8 = 255
	var u16 uint16 = 65535
	var u32 uint32 = 4294967295
	var u64 uint64 = 18446744073709551615
	fmt.Println(u8, u16, u32, u64)

	var i8 int8 = 127
	var i16 int16 = 32767
	var i32 int32 = 2147483647
	var i64 int64 = 9223372036854775807
	fmt.Println(i8, i16, i32, i64)

	var f32 float32 = 1.23
	var f64 float64 = 1.23
	fmt.Println(f32, f64)

	var c64 complex64 = 1 + 2i
	var c128 complex128 = 1 + 2i
	fmt.Println(c64, c128)

	var s string = "abc"
	fmt.Println(s)

	var i int = 123
	var u uint = 123
	var up uintptr = 123
	fmt.Println(i, u, up)

	var bt byte = 123
	var r rune = 'A'
	fmt.Println(bt, r)

	var anySlice = []any{b, u8, i8, f32, c64, s, i, u, up, bt, r}
	fmt.Println(anySlice)

	var comparableFunc = equals[int]
	fmt.Println(comparableFunc(5, 8))

	const (
		i1 int = iota
		i2
		i3
	)

	var e error = nil
	fmt.Println(e)

	var byteSlice []byte
	byteSlice = append(byteSlice, "hello"...)

	list1 := []int{1, 2, 3, 4, 5}
	list2 := make([]int, 4)
	list3 := []int{11, 22, 33, 44, 55, 66}
	n := copy(list2, list1)
	n = copy(list3, list1)
	fmt.Println(n)

	var m map[string]int
	delete(m, "a")
	m = make(map[string]int)
	m["a"] = 1
	delete(m, "a")
	fmt.Println(m)

	m = nil
	fmt.Println(len(m))
	fmt.Println(len("asdf"))

	var slc []int
	fmt.Println(cap(slc))

	_ = make([]int, 0, 10)
	_ = make(map[string]int, 10)
	channel := make(chan int, 10)

	e1 := new(error)
	e1 = lo.ToPtr(fmt.Errorf("abc"))
	fmt.Println(e1)

	c := complex(1, 2)
	rl := real(c)
	img := imag(c)
	fmt.Println(rl, img)

	for i := 0; i < 10; i++ {
		channel <- i
	}
	close(channel)
	for i := 0; i < 12; i++ {
		fmt.Println(<-channel)
	}

	print(1, 2, 3, 4, 5, "%s", "sdf", "\n")
	println(1, 2, 3, 4, 5, "%s", "sdf")

	defer func() {
		if p := recover(); p != nil {
			fmt.Printf("recover found panic: %+v\n", p)
		}
	}()
	panic(123)
}
