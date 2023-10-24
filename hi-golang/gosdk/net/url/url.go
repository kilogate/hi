package main

import (
	"fmt"
	"net/url"
)

func main() {
	testURL()
	testEscape()
}

func testURL() {
	// URL
	url1, _ := url.Parse("http://admin:123456@localhost:8080/a/b/c?name=TOM&name=BOB#chapter1")
	url2, _ := url.Parse("mailto:abc@xxx.com?name=TOM#chapter1")
	fmt.Println(url1, url2)

	// Values
	values := url1.Query()
	names := values["name"]
	fmt.Println(names)
}

func testEscape() {
	queryEscape := url.QueryEscape("my/cool+blog&about,stuff")
	queryUnescape, _ := url.QueryUnescape(queryEscape)
	fmt.Println(queryEscape, queryUnescape)

	pathEscape := url.PathEscape("my/cool+blog&about,stuff")
	pathUnescape, _ := url.PathUnescape(pathEscape)
	fmt.Println(pathEscape, pathUnescape)
}
