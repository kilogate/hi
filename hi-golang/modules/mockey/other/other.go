package other

type other struct{}

func NewOther() *other {
	return new(other)
}

func (o *other) Foo(in string) string {
	return in
}
