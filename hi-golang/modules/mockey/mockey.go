package mockey

func Foo(in string) string {
	return in
}

type A struct{}

func (a *A) Foo(in string) string {
	return in
}

var Bar = 0
