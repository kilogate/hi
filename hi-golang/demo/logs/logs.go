package logs

import (
	"context"
	"fmt"
	"os"
	"runtime"
	"strconv"
	"time"
)

const logIDKey = "LOG_ID"

func CtxInfo(ctx context.Context, msg string, v ...interface{}) {
	fmt.Fprint(os.Stdout, fmt.Sprintf("INFO %s [%s] %s %s\n", getDateTime(), getLogID(ctx), getCaller(), fmt.Sprintf(msg, v...)))
}

func CtxError(ctx context.Context, msg string, v ...interface{}) {
	fmt.Fprint(os.Stderr, fmt.Sprintf("ERROR %s [%s] %s %s\n", getDateTime(), getLogID(ctx), getCaller(), fmt.Sprintf(msg, v...)))
}

func NewContextWithLogID(ctx context.Context) context.Context {
	logID := getLogID(ctx)

	if logID == "" {
		logID = genLogID()
		ctx = context.WithValue(ctx, logIDKey, logID)
	}

	return ctx
}

func genLogID() string {
	return strconv.FormatInt(time.Now().UnixNano(), 10)
}

func getDateTime() string {
	return time.Now().Format("2006-01-02 15:04:05.000")
}

func getLogID(ctx context.Context) string {
	logID, _ := ctx.Value(logIDKey).(string)
	return logID
}

func getCaller() string {
	_, file, line, _ := runtime.Caller(2)
	return fmt.Sprintf("%s:%d", file, line)
}
