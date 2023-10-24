package main

import "fmt"

func main() {
	//testBreakLabel()
	testContinueLabel()
}

func testBreakLabel() {
firstForLabel:
	for i := 0; i < 10; i++ {
		fmt.Printf("line %d\n", i)
		for j := 0; j < 10; j++ {
			fmt.Printf("%d\t", j)
			if j == 5 {
				break firstForLabel
			}
		}
		fmt.Println()
	}
}

func testContinueLabel() {
firstForLabel:
	for i := 0; i < 10; i++ {
		fmt.Printf("line %d\n", i)
		for j := 0; j < 10; j++ {
			fmt.Printf("%d\t", j)
			if j == 5 {
				fmt.Println()
				continue firstForLabel
			}
		}
		fmt.Println()
	}
}
