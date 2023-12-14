package util

import (
	"context"
	"os"

	"hi-golang/demo/logs"
)

func GetPWD() string {
	wd, err := os.Getwd()
	if err != nil {
		logs.CtxError(context.Background(), "GetPwd err, os.Getwd err, err: %+v", err)
		return ""
	}

	return wd
}
