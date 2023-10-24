package main

import (
	"fmt"

	pkgerrors "github.com/pkg/errors"
)

func main() {
	f := pkgerrors.Errorf("user not found")
	fmt.Printf("%v\n", f)  // 没堆栈
	fmt.Printf("%+v\n", f) // 有堆栈

	err := fmt.Errorf("origin err")
	w := pkgerrors.Wrapf(err, "user not found")
	fmt.Printf("%v\n", w)  // 没堆栈
	fmt.Printf("%+v\n", w) // 有堆栈
	fmt.Println(pkgerrors.Cause(w))
}
