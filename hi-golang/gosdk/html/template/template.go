package main

import (
	"fmt"
	"html/template"
	"log"
	"os"
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
	//testTemplate()
	testEscape()
}

// go run gosdk/src/html/template/template.go  > a.html
func testTemplate() {
	// 准备模板
	const templateStr = `
<h1>{{.TotalCount}} issues</h1>
<table>
<tr style='text-align: left'>
  <th>#</th>
  <th>User</th>
  <th>Title</th>
  <th>CreatedAt</th>
</tr>
{{range .Items}}
<tr>
  <td>{{.Number}}</td>
  <td>{{.User.Login}}</td>
  <td>{{.Title | printf "%.32s"}}</td>
  <td>{{.CreatedAt}}</td>
</tr>
{{end}}
</table>
`

	// 解析模板
	templateObj := template.Must(template.New("report").
		Funcs(template.FuncMap{"daysAgo": daysAgo}).
		Parse(templateStr))

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
	err := templateObj.Execute(os.Stdout, dataObj)
	fmt.Println(err)
}

// go run gosdk/src/html/template/template.go  > a.html
func testEscape() {
	const templ = `<p>A: {{.A}}</p><p>B: {{.B}}</p>`
	t := template.Must(template.New("escape").Parse(templ))
	var data struct {
		A string        // untrusted plain text
		B template.HTML // trusted HTML
	}
	data.A = "<b>Hello!</b>"
	data.B = "<b>Hello!</b>"
	if err := t.Execute(os.Stdout, data); err != nil {
		log.Fatal(err)
	}
}

func daysAgo(t time.Time) int {
	return int(time.Since(t).Hours() / 24)
}
