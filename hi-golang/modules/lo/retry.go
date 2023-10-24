package main

import (
	"fmt"
	"log"
	"time"

	"github.com/samber/lo"
)

func main() {
	testAttempt()
	testTransaction()
}

func testAttempt() {
	// 重试，支持配置最大执行次数，支持配置是否继续执行，支持配置执行间隔时间
	maxIteration, since, err := lo.AttemptWhileWithDelay(5, time.Second, func(index int, duration time.Duration) (error, bool) {
		log.Printf("第 %d 次尝试执行, 已过去 %v", index+1, duration)
		if index >= 3 {
			return nil, true
		}
		return fmt.Errorf("执行失败"), true
	})
	fmt.Printf("第 %d 次执行成功，已过去 %v, 报错信息: %v", maxIteration, since, err)
}

func testTransaction() {
	exec := func(state int) (int, error) {
		if state == 3 {
			return 3, fmt.Errorf("执行失败")
		}

		res := state + 1
		log.Printf("exec start, state: %d -> %d\n", state, res)
		return res, nil
	}
	rollback := func(state int) int {
		res := state - 1
		log.Printf("rollback start, state: %d -> %d\n", state, res)
		return res
	}
	// 事务
	res, err := lo.NewTransaction[int]().
		Then(exec, rollback).
		Then(exec, rollback).
		Then(exec, rollback).
		Process(1)
	log.Printf("执行结果: %d, 报错信息: %v", res, err)
}
