package main

import (
	"fmt"
	"log"
	"os"
	"text/template"
	"time"
)

type data struct {
	TotalCount int
	Items      []*item
}

type item struct {
	Number    int
	User      *user
	Title     string
	CreatedAt time.Time
}

type user struct {
	Login string
}

func main() {
	// 准备模板
	const templateStr = `{{.TotalCount}} issues:
{{range .Items}}----------------------------------------
Number: {{.Number}}
User:   {{.User.Login}}
Title:  {{.Title | printf "%.64s"}}
Age:    {{.CreatedAt | daysAgo}} days
{{end}}`

	// 解析模板
	templateObj, err := template.New("report").
		Funcs(template.FuncMap{"daysAgo": daysAgo}).
		Parse(templateStr)
	if err != nil {
		log.Fatal(err)
	}

	// 准备数据
	dataObj := &data{
		TotalCount: 3,
		Items: []*item{
			{
				Number:    1,
				User:      &user{Login: "Tom"},
				Title:     "The first item, 111111111121312431242351253463463463426234653463463434634634634535344232352",
				CreatedAt: time.Now().Add(-37 * time.Hour),
			},
			{
				Number:    2,
				User:      &user{Login: "Bob"},
				Title:     "The second item",
				CreatedAt: time.Now().Add(-38 * time.Hour),
			},
			{
				Number:    3,
				User:      &user{Login: "Alice"},
				Title:     "The third item",
				CreatedAt: time.Now().Add(-39 * time.Hour),
			},
		},
	}

	// 执行模板
	err = templateObj.Execute(os.Stdout, dataObj)
	fmt.Println(err)
}

func daysAgo(t time.Time) int {
	return int(time.Since(t).Hours() / 24)
}
