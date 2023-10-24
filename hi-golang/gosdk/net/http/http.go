package main

import (
	"fmt"
	"io"
	"log"
	"net/http"
)

func testGet() {
	// http get
	resp, err := http.Get("https://www.baidu.com")
	if err != nil {
		log.Fatalf("Get err, err: %+v\n", err)
		return
	}
	defer resp.Body.Close()

	// resp status code
	statusCode := resp.StatusCode
	fmt.Println(statusCode)

	// resp body
	respBodyBytes, err := io.ReadAll(resp.Body)
	if err != nil {
		log.Fatalf("ReadlAll err, err: %+v\n", err)
		return
	}
	fmt.Println(string(respBodyBytes))
}

func testServe() {
	http.HandleFunc("/", func(writer http.ResponseWriter, request *http.Request) {
		fmt.Fprintf(writer, "URL.Path = %q\n", request.URL.Path)
	})

	err := http.ListenAndServe("localhost:8080", nil)
	log.Fatal(err)
}

func testRequest() {
	http.HandleFunc("/", func(writer http.ResponseWriter, request *http.Request) {
		if err := request.ParseForm(); err != nil {
			log.Print(err)
		}
		for k, v := range request.Form {
			fmt.Fprintf(writer, "Form[%q] = %q\n", k, v)
		}
	})

	err := http.ListenAndServe("localhost:8080", nil)
	log.Fatal(err)
}
