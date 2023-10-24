package gomonkey

import (
	"encoding/json"
	"reflect"
	"testing"

	"github.com/agiledragon/gomonkey"
	"github.com/agiledragon/gomonkey/test/fake"
	"github.com/smartystreets/goconvey/convey"
)

var (
	expectOutput = "xxx"
	num          = 10
)

// cd /Users/bytedance/IdeaProjects/hello/modules/github.com/agiledragon/gomonkey && GOARCH=amd64 go test -v -run=TestApplyFunc
func TestApplyFunc(t *testing.T) {
	convey.Convey("TestApplyFunc", t, func() {

		convey.Convey("one func for success", func() {
			patches := gomonkey.ApplyFunc(fake.Exec, func(_ string, _ ...string) (string, error) {
				return expectOutput, nil
			})
			defer patches.Reset()

			output, err := fake.Exec("", "")
			convey.So(err, convey.ShouldEqual, nil)
			convey.So(output, convey.ShouldEqual, expectOutput)
		})

		convey.Convey("one func for fail", func() {
			patches := gomonkey.ApplyFunc(fake.Exec, func(_ string, _ ...string) (string, error) {
				return "", fake.ErrActual
			})
			defer patches.Reset()

			output, err := fake.Exec("", "")
			convey.So(err, convey.ShouldEqual, fake.ErrActual)
			convey.So(output, convey.ShouldEqual, "")
		})

		convey.Convey("two functions", func() {
			patches := gomonkey.ApplyFunc(fake.Exec, func(_ string, _ ...string) (string, error) {
				return expectOutput, nil
			})
			defer patches.Reset()
			patches.ApplyFunc(fake.Belong, func(_ string, _ []string) bool {
				return true
			})

			output, err := fake.Exec("", "")
			convey.So(err, convey.ShouldEqual, nil)
			convey.So(output, convey.ShouldEqual, expectOutput)

			flag := fake.Belong("", nil)
			convey.So(flag, convey.ShouldBeTrue)
		})

		convey.Convey("input and output param", func() {
			patches := gomonkey.ApplyFunc(json.Unmarshal, func(_ []byte, v interface{}) error {
				p := v.(*map[int]int)
				*p = make(map[int]int)
				(*p)[1] = 2
				(*p)[2] = 4
				return nil
			})
			defer patches.Reset()

			var m map[int]int
			err := json.Unmarshal(nil, &m)
			convey.So(err, convey.ShouldEqual, nil)
			convey.So(m[1], convey.ShouldEqual, 2)
			convey.So(m[2], convey.ShouldEqual, 4)
		})

	})
}

// cd /Users/bytedance/IdeaProjects/hello/modules/github.com/agiledragon/gomonkey && GOARCH=amd64 go test -v -run=TestApplyMethod
func TestApplyMethod(t *testing.T) {
	slice := fake.NewSlice()
	var s *fake.Slice

	convey.Convey("TestApplyMethod", t, func() {

		convey.Convey("for success", func() {
			err := slice.Add(1)
			convey.So(err, convey.ShouldEqual, nil)

			patches := gomonkey.ApplyMethod(reflect.TypeOf(s), "Add", func(_ *fake.Slice, _ int) error {
				return nil
			})
			defer patches.Reset()

			err = slice.Add(1)
			convey.So(err, convey.ShouldEqual, nil)

			err = slice.Remove(1)
			convey.So(err, convey.ShouldEqual, nil)
			convey.So(len(slice), convey.ShouldEqual, 0)
		})

		convey.Convey("for already exist", func() {
			err := slice.Add(2)
			convey.So(err, convey.ShouldEqual, nil)

			patches := gomonkey.ApplyMethod(reflect.TypeOf(s), "Add", func(_ *fake.Slice, _ int) error {
				return fake.ErrElemExsit
			})
			defer patches.Reset()

			err = slice.Add(1)
			convey.So(err, convey.ShouldEqual, fake.ErrElemExsit)

			err = slice.Remove(2)
			convey.So(err, convey.ShouldEqual, nil)
			convey.So(len(slice), convey.ShouldEqual, 0)
		})

		convey.Convey("two methods", func() {
			err := slice.Add(3)
			convey.So(err, convey.ShouldEqual, nil)
			defer slice.Remove(3)

			patches := gomonkey.ApplyMethod(reflect.TypeOf(s), "Add", func(_ *fake.Slice, _ int) error {
				return fake.ErrElemExsit
			})
			defer patches.Reset()
			patches.ApplyMethod(reflect.TypeOf(s), "Remove", func(_ *fake.Slice, _ int) error {
				return fake.ErrElemExsit
			})

			err = slice.Add(2)
			convey.So(err, convey.ShouldEqual, fake.ErrElemExsit)

			err = slice.Remove(1)
			convey.So(err, convey.ShouldEqual, fake.ErrElemExsit)
			convey.So(len(slice), convey.ShouldEqual, 1)
			convey.So(slice[0], convey.ShouldEqual, 3)
		})

		convey.Convey("one func and one method", func() {
			err := slice.Add(4)
			convey.So(err, convey.ShouldEqual, nil)
			defer slice.Remove(4)

			patches := gomonkey.ApplyFunc(fake.Exec, func(_ string, _ ...string) (string, error) {
				return expectOutput, nil
			})
			defer patches.Reset()
			patches.ApplyMethod(reflect.TypeOf(s), "Remove", func(_ *fake.Slice, _ int) error {
				return fake.ErrElemNotExsit
			})

			output, err := fake.Exec("", "")
			convey.So(err, convey.ShouldEqual, nil)
			convey.So(output, convey.ShouldEqual, expectOutput)

			err = slice.Remove(1)
			convey.So(err, convey.ShouldEqual, fake.ErrElemNotExsit)
			convey.So(len(slice), convey.ShouldEqual, 1)
			convey.So(slice[0], convey.ShouldEqual, 4)
		})

	})
}

// cd /Users/bytedance/IdeaProjects/hello/modules/github.com/agiledragon/gomonkey && GOARCH=amd64 go test -v -run=TestApplyGlobalVar
func TestApplyGlobalVar(t *testing.T) {
	convey.Convey("TestApplyGlobalVar", t, func() {

		convey.Convey("change", func() {
			patches := gomonkey.ApplyGlobalVar(&num, 150)
			defer patches.Reset()
			convey.So(num, convey.ShouldEqual, 150)
		})

		convey.Convey("recover", func() {
			convey.So(num, convey.ShouldEqual, 10)
		})

	})
}

// cd /Users/bytedance/IdeaProjects/hello/modules/github.com/agiledragon/gomonkey && GOARCH=amd64 go test -v -run=TestApplyFuncVar
func TestApplyFuncVar(t *testing.T) {
	convey.Convey("TestApplyFuncVar", t, func() {

		convey.Convey("for success", func() {
			str := "hello"
			patches := gomonkey.ApplyFuncVar(&fake.Marshal, func(_ interface{}) ([]byte, error) {
				return []byte(str), nil
			})
			defer patches.Reset()

			bytes, err := fake.Marshal(nil)
			convey.So(err, convey.ShouldEqual, nil)
			convey.So(string(bytes), convey.ShouldEqual, str)
		})

		convey.Convey("for fail", func() {
			patches := gomonkey.ApplyFuncVar(&fake.Marshal, func(_ interface{}) ([]byte, error) {
				return nil, fake.ErrActual
			})
			defer patches.Reset()

			_, err := fake.Marshal(nil)
			convey.So(err, convey.ShouldEqual, fake.ErrActual)
		})

	})
}

// cd /Users/bytedance/IdeaProjects/hello/modules/github.com/agiledragon/gomonkey && GOARCH=amd64 go test -v -run=TestApplyFuncSeq -gcflags=-l
func TestApplyFuncSeq(t *testing.T) {
	convey.Convey("TestApplyFuncSeq", t, func() {

		convey.Convey("default times is 1", func() {
			info1 := "hello cpp"
			info2 := "hello golang"
			info3 := "hello gomonkey"
			outputs := []gomonkey.OutputCell{
				{Values: gomonkey.Params{info1, nil}},
				{Values: gomonkey.Params{info2, nil}},
				{Values: gomonkey.Params{info3, nil}},
			}
			patches := gomonkey.ApplyFuncSeq(fake.ReadLeaf, outputs)
			defer patches.Reset()

			output, err := fake.ReadLeaf("")
			convey.So(err, convey.ShouldEqual, nil)
			convey.So(output, convey.ShouldEqual, info1)

			output, err = fake.ReadLeaf("")
			convey.So(err, convey.ShouldEqual, nil)
			convey.So(output, convey.ShouldEqual, info2)

			output, err = fake.ReadLeaf("")
			convey.So(err, convey.ShouldEqual, nil)
			convey.So(output, convey.ShouldEqual, info3)
		})

		convey.Convey("retry success util the third times", func() {
			info1 := "hello cpp"
			outputs := []gomonkey.OutputCell{
				{Values: gomonkey.Params{"", fake.ErrActual}, Times: 2},
				{Values: gomonkey.Params{info1, nil}},
			}
			patches := gomonkey.ApplyFuncSeq(fake.ReadLeaf, outputs)
			defer patches.Reset()

			output, err := fake.ReadLeaf("")
			convey.So(err, convey.ShouldEqual, fake.ErrActual)

			output, err = fake.ReadLeaf("")
			convey.So(err, convey.ShouldEqual, fake.ErrActual)

			output, err = fake.ReadLeaf("")
			convey.So(err, convey.ShouldEqual, nil)
			convey.So(output, convey.ShouldEqual, info1)
		})

		convey.Convey("batch operations failed on the third time", func() {
			info1 := "hello gomonkey"
			outputs := []gomonkey.OutputCell{
				{Values: gomonkey.Params{info1, nil}, Times: 2},
				{Values: gomonkey.Params{"", fake.ErrActual}},
			}
			patches := gomonkey.ApplyFuncSeq(fake.ReadLeaf, outputs)
			defer patches.Reset()

			output, err := fake.ReadLeaf("")
			convey.So(err, convey.ShouldEqual, nil)
			convey.So(output, convey.ShouldEqual, info1)

			output, err = fake.ReadLeaf("")
			convey.So(err, convey.ShouldEqual, nil)
			convey.So(output, convey.ShouldEqual, info1)

			output, err = fake.ReadLeaf("")
			convey.So(err, convey.ShouldEqual, fake.ErrActual)
		})

	})
}

// cd /Users/bytedance/IdeaProjects/hello/modules/github.com/agiledragon/gomonkey && GOARCH=amd64 go test -v -run=TestApplyMethodSeq -gcflags=-l
func TestApplyMethodSeq(t *testing.T) {
	e := &fake.Etcd{}

	convey.Convey("TestApplyMethodSeq", t, func() {

		convey.Convey("default times is 1", func() {
			info1 := "hello cpp"
			info2 := "hello golang"
			info3 := "hello gomonkey"
			outputs := []gomonkey.OutputCell{
				{Values: gomonkey.Params{info1, nil}},
				{Values: gomonkey.Params{info2, nil}},
				{Values: gomonkey.Params{info3, nil}},
			}
			patches := gomonkey.ApplyMethodSeq(reflect.TypeOf(e), "Retrieve", outputs)
			defer patches.Reset()

			output, err := e.Retrieve("")
			convey.So(err, convey.ShouldEqual, nil)
			convey.So(output, convey.ShouldEqual, info1)

			output, err = e.Retrieve("")
			convey.So(err, convey.ShouldEqual, nil)
			convey.So(output, convey.ShouldEqual, info2)

			output, err = e.Retrieve("")
			convey.So(err, convey.ShouldEqual, nil)
			convey.So(output, convey.ShouldEqual, info3)
		})

		convey.Convey("retry success util the third times", func() {
			info1 := "hello cpp"
			outputs := []gomonkey.OutputCell{
				{Values: gomonkey.Params{"", fake.ErrActual}, Times: 2},
				{Values: gomonkey.Params{info1, nil}},
			}
			patches := gomonkey.ApplyMethodSeq(reflect.TypeOf(e), "Retrieve", outputs)
			defer patches.Reset()

			output, err := e.Retrieve("")
			convey.So(err, convey.ShouldEqual, fake.ErrActual)

			output, err = e.Retrieve("")
			convey.So(err, convey.ShouldEqual, fake.ErrActual)

			output, err = e.Retrieve("")
			convey.So(err, convey.ShouldEqual, nil)
			convey.So(output, convey.ShouldEqual, info1)
		})

		convey.Convey("batch operations failed on the third time", func() {
			info1 := "hello gomonkey"
			outputs := []gomonkey.OutputCell{
				{Values: gomonkey.Params{info1, nil}, Times: 2},
				{Values: gomonkey.Params{"", fake.ErrActual}},
			}
			patches := gomonkey.ApplyMethodSeq(reflect.TypeOf(e), "Retrieve", outputs)
			defer patches.Reset()

			output, err := e.Retrieve("")
			convey.So(err, convey.ShouldEqual, nil)
			convey.So(output, convey.ShouldEqual, info1)

			output, err = e.Retrieve("")
			convey.So(err, convey.ShouldEqual, nil)
			convey.So(output, convey.ShouldEqual, info1)

			output, err = e.Retrieve("")
			convey.So(err, convey.ShouldEqual, fake.ErrActual)
		})

	})
}

// cd /Users/bytedance/IdeaProjects/hello/modules/github.com/agiledragon/gomonkey && GOARCH=amd64 go test -v -run=TestApplyFuncVarSeq
func TestApplyFuncVarSeq(t *testing.T) {
	convey.Convey("TestApplyFuncVarSeq", t, func() {

		convey.Convey("default times is 1", func() {
			info1 := "hello cpp"
			info2 := "hello golang"
			info3 := "hello gomonkey"
			outputs := []gomonkey.OutputCell{
				{Values: gomonkey.Params{[]byte(info1), nil}},
				{Values: gomonkey.Params{[]byte(info2), nil}},
				{Values: gomonkey.Params{[]byte(info3), nil}},
			}
			patches := gomonkey.ApplyFuncVarSeq(&fake.Marshal, outputs)
			defer patches.Reset()

			bytes, err := fake.Marshal("")
			convey.So(err, convey.ShouldEqual, nil)
			convey.So(string(bytes), convey.ShouldEqual, info1)

			bytes, err = fake.Marshal("")
			convey.So(err, convey.ShouldEqual, nil)
			convey.So(string(bytes), convey.ShouldEqual, info2)

			bytes, err = fake.Marshal("")
			convey.So(err, convey.ShouldEqual, nil)
			convey.So(string(bytes), convey.ShouldEqual, info3)
		})

		convey.Convey("retry succ util the third times", func() {
			info1 := "hello cpp"
			outputs := []gomonkey.OutputCell{
				{Values: gomonkey.Params{[]byte(""), fake.ErrActual}, Times: 2},
				{Values: gomonkey.Params{[]byte(info1), nil}},
			}
			patches := gomonkey.ApplyFuncVarSeq(&fake.Marshal, outputs)
			defer patches.Reset()

			bytes, err := fake.Marshal("")
			convey.So(err, convey.ShouldEqual, fake.ErrActual)

			bytes, err = fake.Marshal("")
			convey.So(err, convey.ShouldEqual, fake.ErrActual)

			bytes, err = fake.Marshal("")
			convey.So(err, convey.ShouldEqual, nil)
			convey.So(string(bytes), convey.ShouldEqual, info1)
		})

		convey.Convey("batch operations failed on the third time", func() {
			info1 := "hello gomonkey"
			outputs := []gomonkey.OutputCell{
				{Values: gomonkey.Params{[]byte(info1), nil}, Times: 2},
				{Values: gomonkey.Params{[]byte(""), fake.ErrActual}},
			}
			patches := gomonkey.ApplyFuncVarSeq(&fake.Marshal, outputs)
			defer patches.Reset()

			bytes, err := fake.Marshal("")
			convey.So(err, convey.ShouldEqual, nil)
			convey.So(string(bytes), convey.ShouldEqual, info1)

			bytes, err = fake.Marshal("")
			convey.So(err, convey.ShouldEqual, nil)
			convey.So(string(bytes), convey.ShouldEqual, info1)

			bytes, err = fake.Marshal("")
			convey.So(err, convey.ShouldEqual, fake.ErrActual)
		})

	})
}

// cd /Users/bytedance/IdeaProjects/hello/modules/github.com/agiledragon/gomonkey && GOARCH=amd64 go test -v -run=TestPatchPair
func TestPatchPair(t *testing.T) {
	convey.Convey("TestPatchPair", t, func() {
		patchPairs := [][2]interface{}{
			{
				fake.Exec,
				func(_ string, _ ...string) (string, error) {
					return expectOutput, nil
				},
			},
			{
				json.Unmarshal,
				func(_ []byte, v interface{}) error {
					p := v.(*map[int]int)
					*p = make(map[int]int)
					(*p)[1] = 2
					(*p)[2] = 4
					return nil
				},
			},
		}
		patches := gomonkey.NewPatches()
		defer patches.Reset()
		for _, pair := range patchPairs {
			patches.ApplyFunc(pair[0], pair[1])
		}

		output, err := fake.Exec("", "")
		convey.So(err, convey.ShouldEqual, nil)
		convey.So(output, convey.ShouldEqual, expectOutput)

		var m map[int]int
		err = json.Unmarshal(nil, &m)
		convey.So(err, convey.ShouldEqual, nil)
		convey.So(m[1], convey.ShouldEqual, 2)
		convey.So(m[2], convey.ShouldEqual, 4)
	})
}
