package main

import (
	"context"
	"os"

	"hi-golang/demo/logs"
	"hi-golang/demo/util"

	"github.com/gocarina/gocsv"
)

type Person struct {
	Name   string `csv:"name"`
	Age    int    `csv:"age"`
	Gender string `csv:"gender"`
}

func readFromCSV(ctx context.Context) error {
	// 打开文件
	path := "input.csv"
	file, err := os.OpenFile(path, os.O_RDWR|os.O_CREATE, os.ModePerm)
	if err != nil {
		logs.CtxError(ctx, "readFromCSV err, os.OpenFile err, path: %s, err: %+v", path, err)
		return err
	}
	defer file.Close()

	// 反序列化
	var people []*Person
	if err := gocsv.UnmarshalFile(file, &people); err != nil {
		logs.CtxError(ctx, "readFromCSV err, gocsv.UnmarshalFile err, path: %s, err: %+v", path, err)
		return err
	}

	logs.CtxInfo(ctx, "readFromCSV success, result: %s", util.ToString(people))
	return nil
}

func writeToCSV(ctx context.Context) error {
	// 新建文件
	path := "output.csv"
	file, err := os.OpenFile(path, os.O_RDWR|os.O_CREATE, os.ModePerm)
	if err != nil {
		logs.CtxError(ctx, "writeToCSV err, os.OpenFile err, path: %s, err: %+v", path, err)
		return err
	}
	defer file.Close()

	// 序列化
	people := []*Person{
		{Name: "Alice", Age: 21, Gender: "F"},
		{Name: "Bob", Age: 25, Gender: "M"},
	}
	if err := gocsv.MarshalFile(&people, file); err != nil {
		logs.CtxError(ctx, "writeToCSV err, gocsv.MarshalFile err, err: %+v", err)
		return err
	}

	logs.CtxInfo(ctx, "writeToCSV success")
	return nil
}
