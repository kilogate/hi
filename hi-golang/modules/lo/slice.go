package main

import (
	"fmt"
	"log"

	"github.com/apaxa-go/helper/strconvh"
	"github.com/samber/lo"
)

type Stu struct {
	Name string
}

func (s *Stu) Clone() *Stu {
	if s == nil {
		return nil
	}
	return &Stu{Name: s.Name}
}

func main() {
	testSlice()
	testFind()
	testCollection()
	testGroup()
	testStat()
	testSort()
}

func testSlice() {
	// 过滤，保留返回true的元素
	filter := lo.Filter([]string{"a", "b", "c", "123", "321", "A", "B", "C"}, func(item string, index int) bool {
		if len(item) == 3 || index == 0 {
			return true
		}
		return false
	})
	log.Printf("%v\n", filter) //  [a 123 321]

	// 映射，转换所有元素
	mp := lo.Map([]int{11, 22, 33}, func(item int, index int) string {
		return fmt.Sprintf("%d%d", index, item)
	})
	log.Printf("%v\n", mp) // [011 122 233]

	// slice转map，[]T -> map[K]V，支持映射K和V
	sliceToMap := lo.SliceToMap([]*Stu{{"A"}, {"B"}, {"C"}}, func(item *Stu) (string, string) {
		return item.Name, item.Name
	})
	log.Printf("%v\n", sliceToMap) //  map[A:A B:B C:C]

	// 化简，从左向右执行
	reduce := lo.Reduce([]int{1, 2, 3, 4}, func(agg int, item int, index int) int {
		return agg + item
	}, 100)
	log.Printf("%v\n", reduce) // 110

	// 打平
	flatten := lo.Flatten([][]int{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}})
	log.Printf("%v\n", flatten) // [1 2 3 4 5 6 7 8 9]

	// 去重
	uniq := lo.Uniq([]int{1, 3, 5, 6, 7, 3, 1})
	log.Printf("%v\n", uniq) // [1 3 5 6 7]

	// 替换
	replaceAll := lo.ReplaceAll([]int{1, 2, 3, 4, 5}, 1, 10)
	log.Printf("%v\n", replaceAll) // [10 2 3 4 5]

	// 删除，从左向右删除指定个元素
	drop := lo.Drop([]int{1, 2, 3, 4, 5, 6, 7}, 5)
	log.Printf("%v\n", drop) // [6 7]
}

func testFind() {
	letters := []string{"A", "B", "C", "D", "C"}
	subLetters := []string{"A", "B", "C", "D", "C"}

	// 是否包含某元素
	contains := lo.Contains(letters, "B")
	fmt.Println(contains)

	// 是否包含所有元素
	every := lo.Every(letters, subLetters)
	fmt.Println(every)

	// 是否包含任意元素
	some := lo.Some(letters, subLetters)
	fmt.Println(some)

	// 是否不包含任意元素
	none := lo.None(letters, subLetters)
	fmt.Println(none)

	// 去重
	uniques := lo.FindUniques(letters)
	// 重复
	duplicates := lo.FindDuplicates(letters)
	fmt.Println(uniques, duplicates)

	// 最小值
	min := lo.Min(letters)
	// 最大值
	max := lo.Max(letters)
	fmt.Println(min, max)
	// 求和
	sum := lo.Sum([]int{1, 3, 5, 7})
	fmt.Println(sum)
}

func testCollection() {
	letters := []string{"A", "B", "C", "D", "C"}
	otherLetters := []string{"A", "B", "C", "F"}

	// 交集
	intersect := lo.Intersect(letters, otherLetters)
	fmt.Println(intersect)

	// 差集
	left, right := lo.Difference(letters, otherLetters)
	fmt.Println(left, right)

	// 并集
	union := lo.Union(letters, otherLetters)
	fmt.Println(union)

	// 非集
	without := lo.Without(letters, "A", "C")
	fmt.Println(without)

	// 排除空元素
	withoutEmpty := lo.WithoutEmpty(letters)
	fmt.Println(withoutEmpty)
}

func testGroup() {
	// 分块，支持指定每块大小
	ints := []int{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20}
	chunk := lo.Chunk(ints, 4)
	ints[0] = 999             // 结果共用底层数组
	log.Printf("%v\n", chunk) // [[999 2 3 4] [5 6 7 8] [9 10 11 12] [13 14 15 16] [17 18 19 20]]

	// 分组，返回 map[U][]T，无序
	groupBy := lo.GroupBy([]int{1, 2, 3, 4, 5, 2, 4, 6, 8}, func(item int) string {
		return strconvh.FormatInt(item % 2)
	})
	log.Printf("%v\n", groupBy) //  map[0:[2 4 2 4 6 8] 1:[1 3 5]]

	// 分组，返回 [][]T，保序
	partitionBy := lo.PartitionBy([]int{1, 2, 3, 4, 5, 2, 4, 6, 8}, func(item int) string {
		return strconvh.FormatInt(item % 2)
	})
	log.Printf("%v\n", partitionBy) // [[1 3 5] [2 4 2 4 6 8]]
}

func testStat() {
	// 计数，返回指定元素的数量
	count := lo.Count([]string{"a", "b", "c", "123", "321", "A", "B", "C"}, "A")
	log.Printf("%d\n", count) // 1

	// 统计，返回各元素的数量，返回map[T]int
	countValues := lo.CountValues([]string{"A", "B", "C", "123", "321", "A", "B", "C"})
	log.Printf("%v\n", countValues) //  map[123:1 321:1 A:2 B:2 C:2]
}

func testSort() {
	// 倒序
	reverse := lo.Reverse([]int{1, 3, 5, 7, 9})
	log.Printf("%v\n", reverse) // [9 7 5 3 1]

	// 乱序
	shuffle := lo.Shuffle([]int{1, 3, 5, 2, 4, 6})
	log.Printf("%v\n", shuffle) // [6 1 3 5 4 2]

	// 是否有序
	isSorted := lo.IsSorted([]int{0, 1, 2, 3, 4, 5, 6})
	log.Printf("%t\n", isSorted) // true
}
