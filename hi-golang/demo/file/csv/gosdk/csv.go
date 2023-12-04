package main

import (
	"context"
	"encoding/csv"
	"hi-golang/demo/logs"
	"hi-golang/demo/util"
	"io"
	"os"
)

func main() {
	writeToCSV(context.Background())
}

func readFromCSV(ctx context.Context) {
	// 打开文件
	path := "demo/file/csv/gosdk/abc.csv"
	file, err := os.Open(path)
	if err != nil {
		logs.CtxError(ctx, "readFromCSV err, os.Open err, path: %s, err: %+v", path, err)
		return
	}
	defer file.Close()

	// new reader
	reader := csv.NewReader(file)

	// 读取所有记录
	//allRecords, err := reader.ReadAll()
	//if err != nil {
	//	logger.Error(ctx, "readFromCSV err, reader.ReadAll err, err: %+v", err)
	//	return
	//}
	//logger.Info(ctx, "allRecords: %s", util.ToString(ctx, allRecords))

	// 循环读取记录
	for {
		record, err := reader.Read()
		if err == io.EOF {
			break
		}
		if err != nil {
			logs.CtxError(ctx, "readFromCSV err, reader.Read err, err: %+v", err)
			return
		}

		logs.CtxInfo(ctx, "record: %s", util.ToString(ctx, record))
	}
}

func writeToCSV(ctx context.Context) {
	// 创建文件
	path := "demo/file/csv/gosdk/def.csv"
	file, err := os.Create(path)
	if err != nil {
		logs.CtxError(ctx, "writeToCSV err, os.Create err, path: %s, err: %+v", path, err)
		return
	}
	defer file.Close()

	// new writer
	writer := csv.NewWriter(file)
	defer writer.Flush()

	// 写入记录
	record := []string{"Alice", "21", "F"}
	err = writer.Write(record)
	if err != nil {
		logs.CtxError(ctx, "writeToCSV err, writer.Write err, record: %s, err: %+v", util.ToString(ctx, record), err)
		return
	}

	logs.CtxInfo(ctx, "writeToCSV success")
}
