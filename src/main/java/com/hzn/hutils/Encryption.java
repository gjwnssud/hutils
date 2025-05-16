package com.hzn.hutils;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * <p></p>
 *
 * @author hzn
 * @date 2024. 10. 8.
 */
public class Encryption {

  private static final String AES = "AES";
  private static final String AES_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
  private static final String KEY = "sul+hYRjhwXPC+cAzFg37msZS70U8d1Vc9h9k3GSoKw=";
  private static final byte[] IV = new byte[]{10, 11, 12, 13, 14, 15, 9, 8, 7, 6, 5, 4, 3, 2, 1,
      0};

  // AES 암호화
  public static String encrypt(String plainText) throws Exception {
    IvParameterSpec ivParameterSpec = new IvParameterSpec(IV);
    SecretKeySpec secretKey = new SecretKeySpec(Base64.getDecoder().decode(KEY), AES);
    Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
    cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
    byte[] cipherText = cipher.doFinal(plainText.getBytes());
    return Base64.getEncoder().encodeToString(cipherText);
  }

  // AES 복호화
  public static String decrypt(String cipherText) throws Exception {
    IvParameterSpec ivParameterSpec = new IvParameterSpec(IV);
    SecretKeySpec secretKey = new SecretKeySpec(Base64.getDecoder().decode(KEY), AES);
    Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
    cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
    byte[] decryptedText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
    return new String(decryptedText);
  }

  public static void main(String[] args) throws Exception {
    // 키 생성
    KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
    keyGenerator.init(256);
    SecretKey secretKey = keyGenerator.generateKey();
    String sKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
    System.out.println(sKey);
  }
}
