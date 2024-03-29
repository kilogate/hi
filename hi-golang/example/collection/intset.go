package main

import (
	"bytes"
	"fmt"
)

// An IntSet is a set of small non-negative integers.
// Its zero value represents the empty set.
type IntSet struct {
	words []uint64
}

// Has reports whether the set contains the non-negative value x.
func (s *IntSet) Has(x int) bool {
	word, bit := x/64, uint(x%64)
	return word < len(s.words) && s.words[word]&(1<<bit) != 0
}

// Add adds the non-negative value x to the set.
func (s *IntSet) Add(x int) {
	word, bit := x/64, uint(x%64)
	for word >= len(s.words) {
		s.words = append(s.words, 0)
	}
	s.words[word] |= 1 << bit
}

// UnionWith sets s to the union of s and t.
func (s *IntSet) UnionWith(t *IntSet) {
	for i, word := range t.words {
		if i < len(s.words) {
			s.words[i] |= word
		} else {
			s.words = append(s.words, word)
		}
	}
}

// String returns the set as a string of the form "{1 2 3}".
func (s *IntSet) String() string {
	var buf bytes.Buffer
	buf.WriteByte('{')
	for i, word := range s.words {
		if word == 0 {
			continue
		}
		for j := 0; j < 64; j++ {
			if word&(1<<uint(j)) != 0 {
				if buf.Len() > len("{") {
					buf.WriteByte(' ')
				}
				fmt.Fprintf(&buf, "%d", 64*i+j)
			}
		}
	}
	buf.WriteByte('}')
	return buf.String()
}

// Len return the number of elements
func (s *IntSet) Len() int {
	cnt := 0
	for _, word := range s.words {
		for ; word != 0; cnt++ {
			word &= word - 1
		}
	}
	return cnt
}

// Remove x from the set
func (s *IntSet) Remove(x int) {
	if !s.Has(x) {
		return
	}
	word, bit := x/64, uint(x%64)
	s.words[word] &= ^(1 << bit)
}

// Clear remove all elements from the set
func (s *IntSet) Clear() {
	s.words = nil
}

// Copy return a copy of the set
func (s *IntSet) Copy() *IntSet {
	var clone IntSet
	clone.words = make([]uint64, len(s.words))
	copy(clone.words, s.words)
	return &clone
}

func main() {
	var x IntSet
	x.Add(1)
	x.Add(144)
	x.Add(9)
	fmt.Println(x.String())
	fmt.Println(x.Len())

	x.Remove(1)
	fmt.Println(x.String())

	x.Clear()
	fmt.Println(x.String())

	var y IntSet
	y.Add(9)
	y.Add(42)
	fmt.Println(y.String())
	fmt.Println(y.Len())

	x.UnionWith(&y)
	fmt.Println(x.String())
	fmt.Println(x.Has(9), x.Has(123))

	z := x.Copy()
	z.Add(123)
	fmt.Println(z)
	fmt.Println(&x)
}
