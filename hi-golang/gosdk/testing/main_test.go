package testing

import (
	"fmt"
	"os"
	"testing"
)

func TestMain(m *testing.M) {
	fmt.Println("before test run") // setup
	code := m.Run()
	fmt.Println("after test run") // teardown
	os.Exit(code)
}
