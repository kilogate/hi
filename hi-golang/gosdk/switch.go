package main

import "fmt"

func main() {
	//testFallThrough()
	testSwitchTrue()
}

func testFallThrough() {
	strSlice := []string{"a", "b", "c"}

	for _, str := range strSlice {
		fmt.Printf("Round %s\n", str)

		switch str {
		case "a":
			fmt.Println("a")
			fallthrough
		case "b":
			fmt.Println("b")
		case "c":
			fmt.Println("c")
		default:
			fmt.Println("default")
		}
	}
}

func testSwitchTrue() {
	strSlice := []string{"a", "b", "c"}

	for _, str := range strSlice {
		fmt.Printf("Round %s\n", str)

		switch { // 无 tag switch（tagless switch），这和 switch true 等价
		case str == "a":
			fmt.Println("a")
		case str == "b":
			fmt.Println("b")
		case str == "c":
			fmt.Println("c")
		default:
			fmt.Println("default")
		}
	}
}
