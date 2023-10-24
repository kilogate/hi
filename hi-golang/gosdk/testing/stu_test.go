package testing

import (
	"reflect"
	"testing"
)

func TestNewStudent(t *testing.T) {
	type args struct {
		no   int
		name string
	}
	tests := []struct {
		name    string
		args    args
		want    *Student
		wantErr bool
	}{
		{
			name: "正常的测试用例",
			args: args{
				no:   123,
				name: "Tom",
			},
			want: &Student{
				NO:      123,
				Name:    "Tom",
				Chinese: 0,
				English: 0,
				Math:    0,
			},
			wantErr: false,
		},
		{
			name: "异常的测试用例",
			args: args{
				no:   -1,
				name: "",
			},
			want:    nil,
			wantErr: true,
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got, err := NewStudent(tt.args.no, tt.args.name)
			if (err != nil) != tt.wantErr {
				t.Errorf("NewStudent() error = %v, wantErr %v", err, tt.wantErr)
				return
			}
			if !reflect.DeepEqual(got, tt.want) {
				t.Errorf("NewStudent() got = %v, want %v", got, tt.want)
			}
		})
	}
}

func TestStudent_GetAverage(t *testing.T) {
	type fields struct {
		NO      int
		Name    string
		Chinese int
		English int
		Math    int
	}
	tests := []struct {
		name    string
		fields  fields
		want    int
		wantErr bool
	}{
		{
			name: "正常的测试用例",
			fields: fields{
				NO:      123,
				Name:    "Tom",
				Chinese: 12,
				English: 13,
				Math:    14,
			},
			want:    13,
			wantErr: false,
		},
		{
			name: "异常的测试用例",
			fields: fields{
				NO:      123,
				Name:    "Tom",
				Chinese: 0,
				English: 0,
				Math:    0,
			},
			want:    0,
			wantErr: true,
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			s := &Student{
				NO:      tt.fields.NO,
				Name:    tt.fields.Name,
				Chinese: tt.fields.Chinese,
				English: tt.fields.English,
				Math:    tt.fields.Math,
			}
			got, err := s.GetAverage()
			if (err != nil) != tt.wantErr {
				t.Errorf("GetAverage() error = %v, wantErr %v", err, tt.wantErr)
				return
			}
			if got != tt.want {
				t.Errorf("GetAverage() got = %v, want %v", got, tt.want)
			}
		})
	}
}
