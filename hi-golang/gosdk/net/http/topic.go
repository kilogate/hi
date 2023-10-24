package main

import (
	"encoding/json"
	"errors"
	"net/http"
	"path"
	"strconv"
	"time"
)

var TopicCache = []*Topic{
	{
		Id:        1,
		Title:     "Title1",
		Content:   "Content1",
		CreatedAt: time.Now(),
	},
}

type Topic struct {
	Id        int       `json:"id"`
	Title     string    `json:"title"`
	Content   string    `json:"content"`
	CreatedAt time.Time `json:"created_at"`
}

func main() {
	http.HandleFunc("/topic/", handleRequest)
	http.ListenAndServe(":2022", nil)
}

func handleRequest(w http.ResponseWriter, r *http.Request) {
	var err error
	switch r.Method {
	case http.MethodGet:
		err = handleGet(w, r)
	case http.MethodPost:
		err = handlePost(w, r)
	case http.MethodPut:
		err = handlePut(w, r)
	case http.MethodDelete:
		err = handleDelete(w, r)
	}
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}
}

func handleGet(w http.ResponseWriter, r *http.Request) error {
	id, err := strconv.Atoi(path.Base(r.URL.Path))
	if err != nil {
		return err
	}
	topic, err := FindTopic(id)
	if err != nil {
		return err
	}
	output, err := json.MarshalIndent(&topic, "", "\t\t")
	if err != nil {
		return err
	}
	w.Header().Set("Content-Type", "application/json")
	w.Write(output)
	return nil
}

func handlePost(w http.ResponseWriter, r *http.Request) (err error) {
	body := make([]byte, r.ContentLength)
	r.Body.Read(body)
	var topic = new(Topic)
	err = json.Unmarshal(body, &topic)
	if err != nil {
		return
	}

	err = topic.Create()
	if err != nil {
		return
	}
	w.WriteHeader(http.StatusOK)
	return
}

func handlePut(w http.ResponseWriter, r *http.Request) error {
	id, err := strconv.Atoi(path.Base(r.URL.Path))
	if err != nil {
		return err
	}
	topic, err := FindTopic(id)
	if err != nil {
		return err
	}
	body := make([]byte, r.ContentLength)
	r.Body.Read(body)
	json.Unmarshal(body, topic)
	err = topic.Update()
	if err != nil {
		return err
	}
	w.WriteHeader(http.StatusOK)
	return nil
}

func handleDelete(w http.ResponseWriter, r *http.Request) (err error) {
	id, err := strconv.Atoi(path.Base(r.URL.Path))
	if err != nil {
		return
	}
	topic, err := FindTopic(id)
	if err != nil {
		return
	}
	err = topic.Delete()
	if err != nil {
		return
	}
	w.WriteHeader(http.StatusOK)
	return
}

func FindTopic(id int) (*Topic, error) {
	if err := checkIndex(id); err != nil {
		return nil, err
	}

	return TopicCache[id-1], nil
}

func (t *Topic) Create() error {
	t.Id = len(TopicCache) + 1
	t.CreatedAt = time.Now()
	TopicCache = append(TopicCache, t)
	return nil
}

func (t *Topic) Update() error {
	if err := checkIndex(t.Id); err != nil {
		return err
	}
	TopicCache[t.Id-1] = t
	return nil
}

func (t *Topic) Delete() error {
	if err := checkIndex(t.Id); err != nil {
		return err
	}
	TopicCache[t.Id-1] = nil
	return nil
}

func checkIndex(id int) error {
	if id < 1 || id > len(TopicCache) {
		return errors.New("invalid index")
	}
	return nil
}
