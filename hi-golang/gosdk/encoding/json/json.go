package main

import (
	"encoding/json"
	"fmt"
)

func main() {
	testMarshal()
}

func testMarshal() {
	type Stu struct {
		NO   int
		Name string
		Age  int
	}

	s1 := &Stu{
		NO:   1,
		Name: "Tom",
		Age:  18,
	}

	bytes, err := json.Marshal(s1)
	if err != nil {
		fmt.Println("Marshal error:", err)
		return
	}
	jsonStr := string(bytes)
	fmt.Println(jsonStr)

	var s2 Stu
	err = json.Unmarshal([]byte(jsonStr), &s2)
	if err != nil {
		fmt.Println("Unmarshal error:", err)
		return
	}
	fmt.Println(s2)
}
