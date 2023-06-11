package com.exam.util;

import java.security.SecureRandom;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class JwtKeyGeneratorUtil {
    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        byte[] keyBytes = new byte[32]; // 生成一个长度为 32 的随机字节数组作为 Key
        random.nextBytes(keyBytes);
        SecretKey key = new SecretKeySpec(keyBytes, "HmacSHA256"); // 使用 HmacSHA256 算法生成 SecretKey 对象
        System.out.println("JWT Key: " + bytesToHex(keyBytes)); // 将 Key 转换成十六进制字符串输出
    }

    /**
     * 将一个字节数组转换成十六进制字符串。
     * @param bytes 要转换的字节数组
     * @return 返回转换后的十六进制字符串
     */
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;
            hexChars[i * 2] = HEX_ARRAY[v >>> 4];
            hexChars[i * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
}
