package util

import (
	"strings"

	"github.com/google/uuid"
)

func NewUUID() string {
	uuid := uuid.New().String()
	return strings.ReplaceAll(uuid, "-", "")
}
