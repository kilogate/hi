package govaluate

import (
	"testing"

	"github.com/Knetic/govaluate"
	"github.com/smartystreets/goconvey/convey"
)

func Test(t *testing.T) {
	convey.Convey("govalute 测试", t, func() {

		convey.Convey("简单表达式", func() {
			expression, err := govaluate.NewEvaluableExpression("10 > 0")
			convey.So(err, convey.ShouldBeNil)

			res, err := expression.Evaluate(nil)
			convey.So(res, convey.ShouldBeTrue)
			convey.So(err, convey.ShouldBeNil)
		})

		convey.Convey("带变量的表达式", func() {
			expression, err := govaluate.NewEvaluableExpression("foo > 0")
			convey.So(err, convey.ShouldBeNil)

			res, err := expression.Evaluate(map[string]interface{}{
				"foo": -1,
			})
			convey.So(res, convey.ShouldBeFalse)
			convey.So(err, convey.ShouldBeNil)
		})

		convey.Convey("算数运算符", func() {
			expression, err := govaluate.NewEvaluableExpression("(requests_made * requests_succeeded / 100) >= 90")
			convey.So(err, convey.ShouldBeNil)

			parameters := make(map[string]interface{}, 8)
			parameters["requests_made"] = 100
			parameters["requests_succeeded"] = 80

			res, err := expression.Evaluate(parameters)
			convey.So(res, convey.ShouldBeFalse)
			convey.So(err, convey.ShouldBeNil)
		})

		convey.Convey("字符串等值比较", func() {
			expression, err := govaluate.NewEvaluableExpression("http_response_body == 'service is ok'")
			convey.So(err, convey.ShouldBeNil)

			parameters := make(map[string]interface{}, 8)
			parameters["http_response_body"] = "service is ok"

			res, err := expression.Evaluate(parameters)
			convey.So(res, convey.ShouldBeTrue)
			convey.So(err, convey.ShouldBeNil)
		})

		convey.Convey("计算", func() {
			expression, err := govaluate.NewEvaluableExpression("(mem_used / total_mem) * 100")
			convey.So(err, convey.ShouldBeNil)

			parameters := make(map[string]interface{}, 8)
			parameters["total_mem"] = 1024
			parameters["mem_used"] = 512

			res, err := expression.Evaluate(parameters)
			convey.So(res, convey.ShouldEqual, 50)
			convey.So(err, convey.ShouldBeNil)
		})

		convey.Convey("日期比较", func() {
			expression, err := govaluate.NewEvaluableExpression("'2014-01-02' > '2014-01-01 23:59:59'")
			convey.So(err, convey.ShouldBeNil)

			res, err := expression.Evaluate(nil)
			convey.So(res, convey.ShouldBeTrue)
			convey.So(err, convey.ShouldBeNil)
		})

		convey.Convey("多次执行", func() {
			expression, err := govaluate.NewEvaluableExpression("response_time <= 100")
			convey.So(err, convey.ShouldBeNil)

			parameters := make(map[string]interface{}, 8)

			for i := 0; i < 3; i++ {
				parameters["response_time"] = i
				res, err := expression.Evaluate(parameters)
				convey.So(res, convey.ShouldBeTrue)
				convey.So(err, convey.ShouldBeNil)
			}
		})

		convey.Convey("自定义函数", func() {
			functions := map[string]govaluate.ExpressionFunction{
				"strlen": func(args ...interface{}) (interface{}, error) {
					length := len(args[0].(string))
					return (float64)(length), nil
				},
			}

			expString := "strlen('someReallyLongInputString') <= 16"
			expression, err := govaluate.NewEvaluableExpressionWithFunctions(expString, functions)
			convey.So(err, convey.ShouldBeNil)

			res, err := expression.Evaluate(nil)
			convey.So(res, convey.ShouldBeFalse)
			convey.So(err, convey.ShouldBeNil)
		})
	})
}
