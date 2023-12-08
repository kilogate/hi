package goconvey

/*
	导入
	go get github.com/smartystreets/goconvey

	安装
	go install github.com/smartystreets/goconvey

	Web页面
	在测试文件所在目录直接执行 goconvey 命令：cd hi-golang/demo/test/goconvey/ && goconvey
*/

type Service struct {
}

// Hello 方法
func (s *Service) Hello(name string) string {
	return "Hello " + name
}

// Hello 函数
func Hello(name string) string {
	return "Hello " + name
}
