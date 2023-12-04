package main

import (
	"context"
	"github.com/gocarina/gocsv"
	"hi-golang/demo/logs"
	"hi-golang/demo/util"
	"os"
)

type Person struct {
	Name   string `csv:"name"`
	Age    int    `csv:"age"`
	Gender string `csv:"gender"`
}

func main() {
	writeToCSV(context.Background())
}

func readFromCSV(ctx context.Context) {
	// 打开文件
	path := "demo/file/csv/gocsv/data.csv"
	file, err := os.OpenFile(path, os.O_RDWR|os.O_CREATE, os.ModePerm)
	if err != nil {
		logs.CtxError(ctx, "readFromCSV err, os.OpenFile err, path: %s, err: %+v", path, err)
		return
	}
	defer file.Close()

	// 反序列化
	var people []*Person
	if err := gocsv.UnmarshalFile(file, &people); err != nil {
		logs.CtxError(ctx, "readFromCSV err, gocsv.UnmarshalFile err, path: %s, err: %+v", path, err)
		return
	}

	logs.CtxInfo(ctx, "readFromCSV success, result: %s", util.ToString(people))
}

func writeToCSV(ctx context.Context) {
	// 新建文件
	path := "demo/file/csv/gocsv/output.csv"
	file, err := os.OpenFile(path, os.O_RDWR|os.O_CREATE, os.ModePerm)
	if err != nil {
		logs.CtxError(ctx, "writeToCSV err, os.OpenFile err, path: %s, err: %+v", path, err)
		return
	}
	defer file.Close()

	// 序列化
	people := []*Person{
		{Name: "Alice", Age: 21, Gender: "F"},
		{Name: "Bob", Age: 25, Gender: "M"},
	}
	if err := gocsv.MarshalFile(&people, file); err != nil {
		logs.CtxError(ctx, "writeToCSV err, gocsv.MarshalFile err, err: %+v", err)
		return
	}

	logs.CtxInfo(ctx, "writeToCSV success")
}
