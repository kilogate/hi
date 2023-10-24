package main

import (
	"bytes"
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"encoding/base64"
	"fmt"
	"io"
)

func main() {
	originData := "hello, world"
	keyStr := "this is key"

	// CBC模式
	encryptCBC, _ := AesEncryptCBC(originData, keyStr)
	decryptCBC, _ := AesDecryptCBC(encryptCBC, keyStr)
	fmt.Println(encryptCBC, decryptCBC)

	// ECB模式
	encryptECB, _ := AesEncryptECB(originData, keyStr)
	decryptECB, _ := AesDecryptECB(encryptECB, keyStr)
	fmt.Println(encryptECB, decryptECB)

	// CFB模式
	encryptCFB, _ := AesEncryptCFB(originData, keyStr)
	decryptCFB, _ := AesDecryptCFB(encryptCFB, keyStr)
	fmt.Println(encryptCFB, decryptCFB)
}

// AesEncryptCBC CBC模式加密
func AesEncryptCBC(originData string, keyStr string) (string, error) {
	key := generateKey(keyStr)
	aesCipher, err := aes.NewCipher(key)
	if err != nil {
		return "", err
	}
	blockSize := aesCipher.BlockSize()

	originBytes := []byte(originData)
	originBytes = pkcs5Padding(originBytes, blockSize)
	encryptBytes := make([]byte, len(originBytes))

	cbcEncrypter := cipher.NewCBCEncrypter(aesCipher, key[:blockSize])
	cbcEncrypter.CryptBlocks(encryptBytes, originBytes)
	return base64.StdEncoding.EncodeToString(encryptBytes), nil
}

// AesDecryptCBC CBC模式解密
func AesDecryptCBC(encryptData string, keyStr string) (string, error) {
	key := generateKey(keyStr)
	aesCipher, err := aes.NewCipher(key)
	if err != nil {
		return "", err
	}

	blockSize := aesCipher.BlockSize()
	cbcDecrypter := cipher.NewCBCDecrypter(aesCipher, key[:blockSize])

	encryptBytes, err := base64.StdEncoding.DecodeString(encryptData)
	if err != nil {
		return "", err
	}
	decryptBytes := make([]byte, len(encryptBytes))

	cbcDecrypter.CryptBlocks(decryptBytes, encryptBytes)
	decryptBytes = pkcs5UnPadding(decryptBytes)
	return string(decryptBytes), nil
}

// AesEncryptECB ECB模式加密
func AesEncryptECB(originData string, keyStr string) (string, error) {
	key := generateKey(keyStr)
	aesCipher, err := aes.NewCipher(key)
	if err != nil {
		return "", err
	}
	blockSize := aesCipher.BlockSize()

	originBytes := []byte(originData)
	length := (len(originBytes) + blockSize) / blockSize
	plainBytes := make([]byte, length*blockSize)
	copy(plainBytes, originBytes)
	pad := byte(len(plainBytes) - len(originBytes))
	for i := len(originBytes); i < len(plainBytes); i++ {
		plainBytes[i] = pad
	}
	encryptBytes := make([]byte, len(plainBytes))

	// 分组分块加密
	for start, end := 0, blockSize; start <= len(originBytes); start, end = start+blockSize, end+blockSize {
		aesCipher.Encrypt(encryptBytes[start:end], plainBytes[start:end])
	}
	return base64.StdEncoding.EncodeToString(encryptBytes), nil
}

// AesDecryptECB ECB模式解密
func AesDecryptECB(encryptData string, keyStr string) (string, error) {
	key := generateKey(keyStr)
	aesCipher, err := aes.NewCipher(key)
	if err != nil {
		return "", err
	}
	blockSize := aesCipher.BlockSize()

	encryptBytes, err := base64.StdEncoding.DecodeString(encryptData)
	if err != nil {
		return "", err
	}
	decryptBytes := make([]byte, len(encryptBytes))

	// 分组分块解密
	for start, end := 0, blockSize; start < len(encryptBytes); start, end = start+blockSize, end+blockSize {
		aesCipher.Decrypt(decryptBytes[start:end], encryptBytes[start:end])
	}

	trim := 0
	if len(decryptBytes) > 0 {
		trim = len(decryptBytes) - int(decryptBytes[len(decryptBytes)-1])
	}
	return string(decryptBytes[:trim]), nil
}

// AesEncryptCFB CFB模式加密
func AesEncryptCFB(originData string, keyStr string) (string, error) {
	key := generateKey(keyStr)
	aesCipher, err := aes.NewCipher(key)
	if err != nil {
		return "", err
	}
	blockSize := aesCipher.BlockSize()

	originBytes := []byte(originData)
	encryptBytes := make([]byte, blockSize+len(originBytes))
	iv := encryptBytes[:blockSize]
	if _, err := io.ReadFull(rand.Reader, iv); err != nil {
		return "", err
	}

	cfbEncrypter := cipher.NewCFBEncrypter(aesCipher, iv)
	cfbEncrypter.XORKeyStream(encryptBytes[blockSize:], originBytes)
	return base64.StdEncoding.EncodeToString(encryptBytes), nil
}

// AesDecryptCFB CFB模式解密
func AesDecryptCFB(encryptData string, keyStr string) (string, error) {
	key := generateKey(keyStr)
	aesCipher, err := aes.NewCipher(key)
	if err != nil {
		return "", err
	}
	blockSize := aesCipher.BlockSize()

	encryptBytes, err := base64.StdEncoding.DecodeString(encryptData)
	if err != nil {
		return "", err
	}
	iv := encryptBytes[:blockSize]
	encryptBytes = encryptBytes[blockSize:]

	cfbDecrypter := cipher.NewCFBDecrypter(aesCipher, iv)
	cfbDecrypter.XORKeyStream(encryptBytes, encryptBytes)
	return string(encryptBytes), nil
}

// pkcs5Padding PKCS5填充
func pkcs5Padding(cipherBytes []byte, blockSize int) []byte {
	padSize := blockSize - len(cipherBytes)%blockSize
	padBytes := bytes.Repeat([]byte{byte(padSize)}, padSize)
	return append(cipherBytes, padBytes...)
}

// pkcs5UnPadding PKCS5解填充
func pkcs5UnPadding(originBytes []byte) []byte {
	length := len(originBytes)
	padSize := int(originBytes[length-1])
	return originBytes[:(length - padSize)]
}

// generateKey 生成一个16字节（128位）的密钥
func generateKey(keyStr string) []byte {
	realKey := make([]byte, 16)
	keyBytes := []byte(keyStr)
	copy(realKey, keyBytes)
	for i := 16; i < len(keyBytes); {
		for j := 0; j < 16 && i < len(keyBytes); j, i = j+1, i+1 {
			realKey[j] ^= keyBytes[i]
		}
	}
	return realKey
}
