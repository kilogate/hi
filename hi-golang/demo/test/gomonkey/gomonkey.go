package gomonkey

/*
	导入
	go get github.com/agiledragon/gomonkey

	注意事项
	取消内联优化：-gcflags=all=-l
*/

var Service = &service{}

type service struct {
}

// Hello 方法
func (s *service) Hello(name string) string {
	return "Hello " + name
}

// Hi 方法
func (s *service) Hi(name string) string {
	return "Hi " + name
}

// Hello 函数
func Hello(name string) string {
	return "Hello " + name
}

// Hi 函数
func Hi(name string) string {
	return "Hi " + name
}

// num 全局变量
var num = 10

// Hey 函数变量
var Hey = func(name string) string {
	return "Hey " + name
}
