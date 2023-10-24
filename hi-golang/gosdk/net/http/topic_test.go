package main

import (
	"encoding/json"
	"fmt"
	"net/http"
	"net/http/httptest"
	"strings"
	"testing"
)

func Test_handlePost(t *testing.T) {
	mux := http.NewServeMux()
	mux.HandleFunc("/topic/", handleRequest)

	reader := strings.NewReader(`{"title":"The Go Standard Library","content":"It contains many packages."}`)
	r, _ := http.NewRequest(http.MethodPost, "/topic/", reader)

	w := httptest.NewRecorder()

	mux.ServeHTTP(w, r)

	resp := w.Result()
	if resp.StatusCode != http.StatusOK {
		t.Errorf("Response code is %v", resp.StatusCode)
	}
}

func Test_handleGet(t *testing.T) {
	mux := http.NewServeMux()
	mux.HandleFunc("/topic/", handleRequest)

	r, _ := http.NewRequest(http.MethodGet, "/topic/1", nil)

	w := httptest.NewRecorder()

	mux.ServeHTTP(w, r)

	resp := w.Result()
	if resp.StatusCode != http.StatusOK {
		t.Errorf("Response code is %v", resp.StatusCode)
	}

	topic := new(Topic)
	err := json.Unmarshal(w.Body.Bytes(), &topic)
	fmt.Println(err)
	if topic.Id != 1 {
		t.Errorf("Cannot get topic")
	}
}
