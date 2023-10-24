package main

import (
	"fmt"
	"math"
)

type Point struct{ X, Y float64 }

func (p Point) Distance(q Point) float64 {
	return math.Hypot(q.X-p.X, q.Y-p.Y)
}

func main() {
	testMethodValue()
	testMethodExpression()
}

func testMethodValue() {
	p := Point{1, 2}
	q := Point{4, 6}

	// distance: 方法值，其实就是一个函数，指定了接收器，调用的时候不用再指定接收器
	distance := p.Distance
	d := distance(q)
	fmt.Println(d)
}

func testMethodExpression() {
	p := Point{1, 2}
	q := Point{4, 6}

	// distance: 方法表达式，其实就是一个函数，未指定接收器，调用的时候需要再指定接收器
	distance := Point.Distance
	d := distance(p, q)
	fmt.Println(d)
}
