package util

import (
	"context"
	"encoding/json"

	"hi-golang/demo/logs"

	"github.com/apaxa-go/helper/strconvh"
)

func ToBytes(v interface{}) []byte {
	data, err := json.Marshal(v)
	if err != nil {
		logs.CtxError(context.Background(), "ToBytes err, json.Marshal err, v: %+v, err: %+v", v, err)
		return nil
	}

	return data
}

func ToString(v interface{}) string {
	data, err := json.Marshal(v)
	if err != nil {
		logs.CtxError(context.Background(), "ToString err, json.Marshal err, v: %+v, err: %+v", v, err)
		return ""
	}

	return string(data)
}

func ParseInt(s string) int64 {
	if s == "" {
		logs.CtxError(context.Background(), "ParseInt fail, empty param")
		return 0
	}

	res, err := strconvh.ParseInt64(s)
	if err != nil {
		logs.CtxError(context.Background(), "ParseInt err, strconvh.ParseInt64 err, s: %s, err: %+v", s, err)
		return 0
	}

	return res
}
