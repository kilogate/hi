package main

import (
	"crypto"
	"crypto/rand"
	"crypto/rsa"
	"crypto/sha256"
	"crypto/x509"
	"encoding/base64"
	"fmt"
)

func main() {
	// 生成公私钥对
	publicKey, privateKey, _ := GenKey()

	// 序列化公钥
	publicKeyStr := MarshalPKCS1PublicKey(publicKey)
	publicKey, _ = ParsePKCS1PublicKey(publicKeyStr)

	// 序列化私钥
	privateKeyStr := MarshalPKCS1PrivateKey(privateKey)
	privateKey, _ = ParsePKCS1PrivateKey(privateKeyStr)

	// RSA加解密
	rsaEncrypt, _ := RSAEncrypt("hello, world", publicKey)
	rsaDecrypt, _ := RSADecrypt(rsaEncrypt, privateKey)
	fmt.Println(rsaEncrypt, rsaDecrypt)

	// RSA签名和验签
	key := "key"
	sign, _ := RSASign(key, privateKey)
	verify, _ := RSAVerify(key, sign, publicKey)
	fmt.Println(sign, verify)
}

// GenKey 生成公私钥对
func GenKey() (*rsa.PublicKey, *rsa.PrivateKey, error) {
	privateKey, err := rsa.GenerateKey(rand.Reader, 2048)
	if err != nil {
		return nil, nil, err
	}
	return &privateKey.PublicKey, privateKey, nil
}

// MarshalPKCS1PublicKey 序列化公钥
func MarshalPKCS1PublicKey(publicKey *rsa.PublicKey) string {
	publicKeyBytes := x509.MarshalPKCS1PublicKey(publicKey)
	return base64.StdEncoding.EncodeToString(publicKeyBytes)
}

// ParsePKCS1PublicKey 反序列化公钥
func ParsePKCS1PublicKey(publicKeyStr string) (*rsa.PublicKey, error) {
	privateKeyBytes, err := base64.StdEncoding.DecodeString(publicKeyStr)
	if err != nil {
		return nil, err
	}
	privateKey, err := x509.ParsePKCS1PublicKey(privateKeyBytes)
	if err != nil {
		return nil, err
	}
	return privateKey, err
}

// MarshalPKCS1PrivateKey 序列化私钥
func MarshalPKCS1PrivateKey(privateKey *rsa.PrivateKey) string {
	privateKeyBytes := x509.MarshalPKCS1PrivateKey(privateKey)
	return base64.StdEncoding.EncodeToString(privateKeyBytes)
}

// ParsePKCS1PrivateKey 反序列化私钥
func ParsePKCS1PrivateKey(privateKeyStr string) (*rsa.PrivateKey, error) {
	privateKeyBytes, err := base64.StdEncoding.DecodeString(privateKeyStr)
	if err != nil {
		return nil, err
	}
	privateKey, err := x509.ParsePKCS1PrivateKey(privateKeyBytes)
	if err != nil {
		return nil, err
	}
	return privateKey, err
}

// RSAEncrypt RSA加密
func RSAEncrypt(originData string, publicKey *rsa.PublicKey) (string, error) {
	originBytes := []byte(originData)
	encryptBytes, err := rsa.EncryptOAEP(sha256.New(), rand.Reader, publicKey, originBytes, nil)
	if err != nil {
		return "", err
	}
	return base64.StdEncoding.EncodeToString(encryptBytes), nil
}

// RSADecrypt RSA解密
func RSADecrypt(encryptData string, privateKey *rsa.PrivateKey) (string, error) {
	encryptBytes, err := base64.StdEncoding.DecodeString(encryptData)
	if err != nil {
		return "", err
	}
	decryptBytes, err := rsa.DecryptOAEP(sha256.New(), rand.Reader, privateKey, encryptBytes, nil)
	if err != nil {
		return "", err
	}
	return string(decryptBytes), nil
}

// RSASign RSA签名
func RSASign(originData string, privateKey *rsa.PrivateKey) (string, error) {
	shaBytes := sha256.Sum256([]byte(originData))
	signBytes, err := rsa.SignPSS(rand.Reader, privateKey, crypto.SHA256, shaBytes[:], nil)
	if err != nil {
		return "", err
	}
	return base64.StdEncoding.EncodeToString(signBytes), nil
}

// RSAVerify RSA验签
func RSAVerify(originData, sign string, publicKey *rsa.PublicKey) (bool, error) {
	shaBytes := sha256.Sum256([]byte(originData))
	signBytes, err := base64.StdEncoding.DecodeString(sign)
	if err != nil {
		return false, err
	}
	err = rsa.VerifyPSS(publicKey, crypto.SHA256, shaBytes[:], signBytes, nil)
	if err != nil {
		return false, err
	}
	return true, nil
}
