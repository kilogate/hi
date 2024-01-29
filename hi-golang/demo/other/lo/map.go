package main

import (
	"fmt"

	"github.com/samber/lo"
)

func main() {
	testKeys()
	testValueOr()
	testPick()
	testOmit()
	testInvert()
	testAssign()
	testMap()
}

func testKeys() {
	mp := map[string]string{
		"a": "A",
		"b": "B",
		"c": "C",
	}

	// Keys
	keys := lo.Keys(mp)
	// Values
	values := lo.Values(mp)
	fmt.Println(keys, values)

	// Entries
	entries := lo.Entries(mp)
	// FromEntries
	nm := lo.FromEntries(entries)
	fmt.Println(entries, nm)
}

func testValueOr() {
	mp := map[string]string{
		"a": "A",
		"b": "B",
		"c": "C",
	}

	// 根据 key 取值，取不到返回降级值
	valueOr := lo.ValueOr(mp, "d", "D")
	fmt.Println(valueOr)
}

func testPick() {
	mp := map[string]string{
		"a": "A",
		"b": "B",
		"c": "C",
	}

	// 根据 key 和 value 选择元素
	pickBy := lo.PickBy(mp, func(key string, value string) bool {
		return key == "a" && value == "A"
	})
	fmt.Println(pickBy)

	// 根据 key 选择元素
	pickByKeys := lo.PickByKeys(mp, []string{"a", "b"})
	fmt.Println(pickByKeys)

	// 根据 value 选择元素
	pickByValues := lo.PickByValues(mp, []string{"A", "B"})
	fmt.Println(pickByValues)
}

func testOmit() {
	mp := map[string]string{
		"a": "A",
		"b": "B",
		"c": "C",
	}

	// 根据 key 和 value 忽略元素
	omitBy := lo.OmitBy(mp, func(key string, value string) bool {
		return key == "a" && value == "A"
	})
	// 根据 key 忽略元素
	omitByKeys := lo.OmitByKeys(mp, []string{"a", "b"})
	// 根据 value 忽略元素
	omitByValues := lo.OmitByValues(mp, []string{"A", "B"})
	fmt.Println(omitBy, omitByKeys, omitByValues)
}

func testInvert() {
	mp := map[string]string{
		"a": "A",
		"b": "B",
		"c": "C",
	}

	// KV 反转，map[K]V -> map[V]K
	invert := lo.Invert(mp)
	fmt.Println(invert)
}

func testAssign() {
	mp := map[string]string{
		"a": "A",
		"b": "B",
		"c": "C",
	}
	otherMap := map[string]string{
		"a": "AA",
		"b": "BB",
		"c": "CC",
	}

	// 合并 map，后者覆盖前者
	assign := lo.Assign(mp, otherMap)
	fmt.Println(assign)
}

func testMap() {
	mp := map[string]string{
		"a": "A",
		"b": "B",
		"c": "C",
	}

	// 根据KV生成新的K
	mapKeys := lo.MapKeys(mp, func(value string, key string) string {
		return key + value
	})
	fmt.Println(mapKeys)

	// 根据KV生成新的V
	mapValues := lo.MapValues(mp, func(value string, key string) string {
		return key + value
	})
	fmt.Println(mapValues)

	// 根据KV生成新的KV
	mapEntries := lo.MapEntries(mp, func(key string, value string) (string, string) {
		return "Key:" + key, "Value:" + value
	})
	fmt.Println(mapEntries)

	// map转slice
	mapToSlice := lo.MapToSlice(mp, func(key string, value string) string {
		return key + value
	})
	fmt.Println(mapToSlice)
}
