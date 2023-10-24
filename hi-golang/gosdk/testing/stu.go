package testing

import "fmt"

type Student struct {
	NO   int
	Name string

	Chinese int
	English int
	Math    int
}

func NewStudent(no int, name string) (*Student, error) {
	if no < 1 || len(name) < 1 {
		return nil, fmt.Errorf("invalid param")
	}
	stu := new(Student)
	stu.NO = no
	stu.Name = name
	return stu, nil
}

func (s *Student) GetAverage() (int, error) {
	if s == nil {
		return 0, fmt.Errorf("nil Student")
	}

	total := s.Chinese + s.English + s.Math
	if total == 0 {
		return 0, fmt.Errorf("total is 0")
	}
	return total / 3, nil
}
