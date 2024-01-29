package main

import (
	"context"
	"encoding/csv"
	"io"
	"os"

	"hi-golang/demo/logs"
	"hi-golang/demo/util"
)

func main() {
	ctx := util.NewCtx()

	err := readFromCSV(ctx)
	if err != nil {
		logs.CtxError(ctx, "readFromCSV err, err: %+v", err)
	}

	err = writeToCSV(ctx)
	if err != nil {
		logs.CtxError(ctx, "writeToCSV err, err: %+v", err)
	}
}

func readFromCSV(ctx context.Context) error {
	// 打开文件
	path := "input.csv"
	file, err := os.Open(path)
	if err != nil {
		logs.CtxError(ctx, "readFromCSV err, os.Open err, path: %s, err: %+v", path, err)
		return err
	}
	defer file.Close()

	// new reader
	reader := csv.NewReader(file)

	// 读取所有记录
	//allRecords, err := reader.ReadAll()
	//if err != nil {
	//	logs.CtxError(ctx, "readFromCSV err, reader.ReadAll err, err: %+v", err)
	//	return err
	//}
	//logs.CtxInfo(ctx, "allRecords: %s", util.ToString(allRecords))

	// 循环读取记录
	for {
		record, err := reader.Read()
		if err == io.EOF {
			break
		}
		if err != nil {
			logs.CtxError(ctx, "readFromCSV err, reader.Read err, err: %+v", err)
			return err
		}

		logs.CtxInfo(ctx, "record: %s", util.ToString(record))
	}

	return nil
}

func writeToCSV(ctx context.Context) error {
	// 创建文件
	path := "output.csv"
	file, err := os.Create(path)
	if err != nil {
		logs.CtxError(ctx, "writeToCSV err, os.Create err, path: %s, err: %+v", path, err)
		return err
	}
	defer file.Close()

	// new writer
	writer := csv.NewWriter(file)
	defer writer.Flush()

	// 写入记录
	record := []string{"Alice", "21", "F"}
	err = writer.Write(record)
	if err != nil {
		logs.CtxError(ctx, "writeToCSV err, writer.Write err, record: %s, err: %+v", util.ToString(record), err)
		return err
	}

	logs.CtxInfo(ctx, "writeToCSV success")
	return nil
}
