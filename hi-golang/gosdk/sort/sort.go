package main

import (
	"fmt"
	"sort"
)

func main() {
	testSortType()
	testSortFunc()
}

func testSortType() {
	// IntSlice
	var ints sort.IntSlice = []int{1, 3, 6, 7, 4, 5, 2, 8, 9, 0}
	ints.Sort()
	fmt.Println(ints)

	// Float64Slice
	var float64s sort.Float64Slice = []float64{6.6, 2.2, 3.3}
	sort.Sort(float64s)
	fmt.Println(float64s)

	// StringSlice
	var strings sort.StringSlice
	strings = append(strings, "A")
	strings = append(strings, "C")
	strings = append(strings, "B")
	strings.Sort()
	fmt.Println(strings)
}

func testSortFunc() {
	// Ints
	ints := []int{1, 3, 6, 7, 4, 5, 2, 8, 9, 0}
	sort.Ints(ints)
	fmt.Println(ints)

	// Float64s
	float64s := []float64{6.6, 2.2, 3.3}
	sort.Float64s(float64s)
	fmt.Println(float64s)

	// Strings
	strings := []string{"B", "A", "C"}
	sort.Strings(strings)
	fmt.Println(strings)

	// AreSorted
	fmt.Println(sort.IntsAreSorted(ints))
	fmt.Println(sort.Float64sAreSorted(float64s))
	fmt.Println(sort.StringsAreSorted(strings))

	// Reverse
	var intSlice sort.IntSlice = []int{1, 3, 6, 7, 4, 5, 2, 8, 9, 0}
	sort.Sort(sort.Reverse(intSlice))
	fmt.Println(intSlice)

	// Stable
	sort.Stable(intSlice)
}
