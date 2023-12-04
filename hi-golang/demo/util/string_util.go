package util

import (
	"context"
	"encoding/json"
	"github.com/apaxa-go/helper/strconvh"
	"hi-golang/demo/logs"
)

func ToBytes(ctx context.Context, v interface{}) []byte {
	data, err := json.Marshal(v)
	if err != nil {
		logs.CtxError(ctx, "ToBytes err, json.Marshal err, v: %+v, err: %+v", v, err)
		return nil
	}

	return data
}

func ToString(ctx context.Context, v interface{}) string {
	data, err := json.Marshal(v)
	if err != nil {
		logs.CtxError(ctx, "ToString err, json.Marshal err, v: %+v, err: %+v", v, err)
		return ""
	}

	return string(data)
}

func ParseInt(ctx context.Context, s string) int64 {
	if s == "" {
		logs.CtxError(ctx, "ParseInt fail, empty param")
		return 0
	}

	res, err := strconvh.ParseInt64(s)
	if err != nil {
		logs.CtxError(ctx, "ParseInt err, strconvh.ParseInt64 err, s: %s, err: %+v", s, err)
		return 0
	}

	return res
}
